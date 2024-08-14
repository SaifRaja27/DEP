public class server {
    import socket
import threading

            HOST = '127.0.0.1'
    PORT = 12345

    clients = []

    def handle_client(client_socket, addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    while True:
            try:
    message = client_socket.recv(1024).decode('utf-8')
            if message:
    print(f"[{addr}] {message}")
    broadcast(message, client_socket)
            else:
                    break
    except:
            clients.remove(client_socket)
            client_socket.close()
    print(f"[DISCONNECTED] {addr} disconnected.")
            break

    def broadcast(message, client_socket):
            for client in clients:
            if client != client_socket:
            try:
            client.send(message.encode('utf-8'))
    except:
            client.close()
            clients.remove(client)

    def start_server():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            server.bind((HOST, PORT))
            server.listen()

    print(f"[STARTED] Server started on {HOST}:{PORT}")
    while True:
    client_socket, addr = server.accept()
            clients.append(client_socket)
    thread = threading.Thread(target=handle_client, args=(client_socket, addr))
            thread.start()
    print(f"[ACTIVE CONNECTIONS] {threading.active_count() - 1}")

if __name__ == "__main__":
    start_server()

}
