package ictgradschool.industry.lab13.ex03;

import ictgradschool.industry.lab13.examples.example03.Consumer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class to implement serial processing of Transactions on a single
 * BankAccount object.
 *
 * This program simple acquires a List of Transaction objects from
 * TransactionGenerator and applies each Transaction to the BankAccount object.
 * The balance of the BankAccount is initially zero. Hacing applied all
 * Transactions to the account, SerialBankingApp displays the final balance of
 * the account.
 *
 */
public class ConcurrentBankingApp {
	public static void main(String[] args) {
		final BlockingQueue<Transaction> queue = new ArrayBlockingQueue<>(10);
				BankAccount account = new BankAccount();

				Thread producer = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							List<Transaction> transactions  = TransactionGenerator.readDataFile();

							for (Transaction transaction: transactions) {
								queue.put(transaction);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});


				Thread consumer1 = new Thread(new Consumer(queue, account));
				Thread consumer2 = new Thread(new Consumer(queue, account));

				producer.start();
				consumer1.start();
				consumer2.start();

				try {
					producer.join();
					System.out.print("Producer finished");

					consumer1.interrupt();
					consumer2.interrupt();

					consumer1.join();
					consumer2.join();

					// Print the final balance after applying all Transactions.
				System.out.println("Final balance: " + account.getFormattedBalance());
				}
				catch(InterruptedException e) {
					System.out.println("Interruption while blocking on join");
				}

	}
	}
