public class client {
    import socket
import threading

            HOST = '127.0.0.1'
    PORT = 12345

    def receive_messages(client_socket):
            while True:
            try:
    message = client_socket.recv(1024).decode('utf-8')
            if message:
    print(message)
            else:
                    break
    except:
    print("An error occurred!")
            client_socket.close()
                    break

    def send_messages(client_socket):
            while True:
    message = input()
        try:
                client_socket.send(message.encode('utf-8'))
    except:
    print("An error occurred!")
            client_socket.close()
                    break

    def start_client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            client_socket.connect((HOST, PORT))

    thread_receive = threading.Thread(target=receive_messages, args=(client_socket,))
            thread_receive.start()

    thread_send = threading.Thread(target=send_messages, args=(client_socket,))
            thread_send.start()

            if __name__ == "__main__":
    start_client()

}
