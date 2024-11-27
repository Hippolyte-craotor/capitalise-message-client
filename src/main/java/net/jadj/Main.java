package net.jadj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String SERVER_HOST = "localhost";
        int SERVER_PORT = 5000;

        try {
            // Connexion au serveur
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connecté au serveur de capitalisation.");

            // Préparation pour envoyer et recevoir des messages
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String message;
            System.out.println("Tapez vos messages (tapez 'bye' pour quitter) :");

            // Boucle pour envoyer des messages
            while (true) {
                System.out.print("Vous : ");
                message = scanner.nextLine();
                output.println(message); // Envoi du message au serveur

                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("Déconnexion...");
                    break;
                }

                // Lecture de la réponse du serveur
                String response = input.readLine();
                System.out.println("Serveur : " + response);
            }

            // Fermeture des ressources
            scanner.close();
            output.close();
            input.close();
            socket.close();
        } catch (IOException exception) {
            System.err.println("Erreur client : " + exception.getMessage());
        }
    }
}