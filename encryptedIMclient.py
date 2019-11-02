import argparse
import socket
from cryption_pb2 import cryption
import threading
import struct
import hashlib
import signal
from Crypto.Cipher import AES
from Crypto.Hash import HMAC, SHA256
from Crypto import Random
from Crypto.Util.Padding import pad, unpad
import sys
import os


def main():
    crypt = cryption()
    parser = argparse.ArgumentParser(add_help=False)
    parser.add_argument('-p', '--port', dest='port', required=True)
    parser.add_argument('-n', '--nickname', dest='nickname', required=True)
    parser.add_argument('-s', '--servername', dest='servername', required=True)
    parser.add_argument('-c', '--confidentialitykey', dest='confidentialitykey', required=True)
    parser.add_argument('-a', '--authenticitykey', dest='authenticitykey', required=True)
    args = parser.parse_args()
    s = socket.socket()
    host = args.servername
    port = int(args.port)
    s.connect((host, port))

    name = str(args.nickname)
    ck = args.confidentialitykey
    ak = args.authenticitykey
    ck_m = hashlib.sha256()
    ak_m = hashlib.sha256()
    ck_m.update(ck.encode())
    ak_m.update(ak.encode())
    hashed_ck = ck_m.digest()
    hashed_ak = ak_m.digest()

    def sending():
        try:
            while True:
                sent_mes = input()
                if sent_mes.lower() != 'exit':
                    plaintext = name + ":" + sent_mes
                    crypt.IV = Random.new().read(AES.block_size)
                    cipher = AES.new(hashed_ck, AES.MODE_CBC, crypt.IV)
                    ciphertext = cipher.encrypt(pad(plaintext.encode(), AES.block_size))
                    crypt.ciphertext = ciphertext
                    h = HMAC.new(hashed_ak, digestmod=SHA256)
                    h.update(ciphertext)
                    hmac = h.digest()
                    crypt.hmac = hmac
                    data = crypt.SerializeToString()
                    s.send(struct.pack("!i", len(data)))
                    s.send(data)
                else:
                    s.close()
                    sys.exit(0)
        except EOFError:
            s.close()
            sys.exit(0)
        except OSError:
            s.close()
            sys.exit(0)

    def receiving():
        while True:
            data_len = s.recv(4, socket.MSG_WAITALL)
            unpacked_len = struct.unpack("!i", data_len)
            data = s.recv(unpacked_len[0], socket.MSG_WAITALL)
            if len(data) != 0:
                received_crypt = cryption()
                received_crypt.ParseFromString(data)
                received_ciphertext = received_crypt.ciphertext
                received_hmac = received_crypt.hmac
                received_IV = received_crypt.IV
                h = HMAC.new(hashed_ak, digestmod=SHA256)
                h.update(received_ciphertext)
                try:
                    h.verify(received_hmac)
                except ValueError:
                    print("Wrong key or Wrong message", flush=True)
                    break
                cipher = AES.new(hashed_ck, AES.MODE_CBC, received_IV)
                try:
                    received_plaintext = unpad(cipher.decrypt(received_ciphertext), AES.block_size)
                    print(received_plaintext.decode(), flush=True)
                except KeyError:
                    print("Wrong key or Bad message", flush=True)
                    break
                except ValueError:
                    print("Bad message", flush=True)
                    break
        os._exit(0)

    def handler(signum, frame):
        s.close()
        sys.exit(0)
    signal.signal(signal.SIGINT, handler)

    t1 = threading.Thread(target=sending)
    t2 = threading.Thread(target=receiving)
    t1.start()
    t2.start()


if __name__ == '__main__':
    main()
