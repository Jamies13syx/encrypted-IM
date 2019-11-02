import socket
import select
import signal
import sys
import struct
import argparse


def main():
    parser = argparse.ArgumentParser(add_help=False)
    parser.add_argument('-p', '--port', dest='port', required=True)
    args = parser.parse_args()
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    port = int(args.port)
    server.bind(('', port))
    server.listen(10)
    inputs = [server]

    def handler(signum, frame):
        server.close()
        sys.exit(0)
    signal.signal(signal.SIGINT, handler)

    while True:
        try:
            (readable, _, _) = select.select(inputs, [], [])
            for r in readable:
                if r == server:
                    conn, addr = r.accept()
                    inputs.append(conn)
                else:
                    try:
                        data_len = r.recv(4, socket.MSG_WAITALL)
                        if len(data_len) != 0:
                            unpacked_len = struct.unpack("!i", data_len)
                            data = r.recv(unpacked_len[0], socket.MSG_WAITALL)
                            for conn in inputs[1:]:
                                conn.send(struct.pack("!i", len(data)))
                                conn.send(data)
                        else:
                            pass
                    except ConnectionResetError:
                        inputs.remove(r)
        except KeyboardInterrupt:
            server.close()
            exit(0)


if __name__ == '__main__':
    main()
