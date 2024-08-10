package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 *
	 * When add task is clicked: Create a JOptionPane to ask the user for a task and
	 * add it to an ArrayList
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: Create a JOptionPane to prompt the
	 * user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Create a JOptionPane to Prompt the user
	 * for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */

	JButton addTask = new JButton();
	JButton viewTasks = new JButton();
	JButton removeTask = new JButton();
	JButton saveList = new JButton();
	JButton loadList = new JButton();
	JButton updateList = new JButton();

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	ArrayList<Task> taskList = new ArrayList<Task>();

	public void run() {
		addTask.addActionListener(this);
		viewTasks.addActionListener(this);
		removeTask.addActionListener(this);
		saveList.addActionListener(this);
		loadList.addActionListener(this);
		updateList.addActionListener(this);

		frame.add(panel);
		panel.add(addTask);
		panel.add(viewTasks);
		panel.add(removeTask);
		panel.add(saveList);
		panel.add(loadList);
		panel.add(updateList);

		frame.setVisible(true);
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addTask.setText("Add Task");
		viewTasks.setText("View Tasks");
		removeTask.setText("Remove Task");
		saveList.setText("Save List");
		loadList.setText("Load List");
		updateList.setText("Update List");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addTask) {
			String userInput = JOptionPane.showInputDialog("Enter task. ");

			Task newTask = new Task(taskList.size(), userInput);

			taskList.add(newTask);

		} else if (e.getSource() == viewTasks) {

			Stream<Task> stream = taskList.stream();
			// can you stream infinitely with the task list thing
			stream.forEach((thing) -> System.out.println("Description: " + thing.desc() + " Order: " + thing.order()));
			System.out.println("Stream printed. (at least in theory)");

		} else if (e.getSource() == removeTask) {
			String name = JOptionPane.showInputDialog("Which task do you want to delete? Enter task name. ");
			for(int i = 0; i < taskList.size(); i++) {
				if(taskList.get(i).desc().equalsIgnoreCase(name)) {
					taskList.remove(i);
					update();
					System.out.println("Theoretically, removed and updated. ");
				}
			}
			
		} else if (e.getSource() == saveList) {
			 
			
			try {
				
				BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
				
				//Stream<Task> stream = taskList.stream();
				//too difficult to do with a stream wtf does "addup must be final" mean
				
				for(int i = 0; i < taskList.size(); i++) {
					//writer.write("Description: " + taskList.get(i).desc() + " Order: " + (taskList.get(i).order()+1) + "\n");
					writer.write(taskList.get(i).desc() + "\n");
				}
				writer.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} else if (e.getSource() == loadList) {
			taskList.clear();
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
				long numberLines = 0;
				try(LineNumberReader linereader = new LineNumberReader(new FileReader("tasks.txt"))){
					linereader.skip(Long.MAX_VALUE);
					numberLines = linereader.getLineNumber() ;
					
				}
				System.out.println(numberLines);
				for(int i = 0; i < numberLines; i++) {
					 String line = "";
						
						line =	reader.readLine();
						
						
						taskList.add(new Task(i, line));
				}
					
					
				
					
				
				
				
				reader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Size: " + taskList.size());
			
			
			
		} else if (e.getSource() == updateList) {
			update();
		}
	}

	void update() {
		for (int j = 0; j < taskList.size(); j++) {
			for (int i = 0; i < taskList.size()-1; i++) {
				if(taskList.get(i).order() > taskList.get(i+1).order()) {
					Task bucket1 = taskList.get(i);
					taskList.set(i, taskList.get(i+1));
					taskList.set(i+1, bucket1);
				}
				
			}
		}for(int i = 0; i < taskList.size(); i++) {
			taskList.get(i).setOrder(i);
		}

	}

}

