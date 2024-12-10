package CLI;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        int totalTickets = 0;
        int ticketReleaseRate = 0;
        int customerRetrievalRate = 0;
        int maxTicketCapacity = 0;


        Scanner sc = new Scanner(System.in);


        while (true) {

            System.out.print("Enter the Total Number of Tickets: ");
            try {
                totalTickets = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
                continue;
            }
            if (0 >= totalTickets) {
                System.out.println("Total tickets cannot be less than 0");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Enter the Ticket Release Rate: ");
            try {
                ticketReleaseRate = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
                continue;
            }
            if (0 >= ticketReleaseRate) {
                System.out.println("Ticker Release Rate cannot be less than 0");
                continue;
            }
            break;
        }

        while (true) {

            System.out.print("Enter the Customer Retrieval Rate: ");
            try {
                customerRetrievalRate = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
                continue;
            }
            if (0 >= customerRetrievalRate) {
                System.out.println("Customer Retrieval Rate cannot be less than 0");
                continue;
            }
            break;
        }

        while (true) {

            System.out.print("Enter the Maximum Ticket Capacity");
            try {
                maxTicketCapacity = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number");
                continue;
            }
            if (0 >= maxTicketCapacity) {
                System.out.println("Max Ticket Capacity cannot be less than 0");
                continue;
            } else if (maxTicketCapacity < totalTickets) {
                System.out.println("Max Ticket Capacity cannot be less than total tickets");
                continue;
            }
            break;
        }

        System.out.println();
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate);
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("Total Tickets: " + maxTicketCapacity);
        System.out.println();

        Configuration configuration = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        configuration.saveConfiguration("../Files/configuration.json");
    }
}