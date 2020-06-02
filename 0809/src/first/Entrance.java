package first;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class Entrance extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	static int count=0;
	static int n=0;//指定忽略的行
	static int index=1;//指定当前的序号
	
	
	MongoClient mongoClient=new MongoClient();	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Entrance frame = new Entrance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public Entrance() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		//label
		//start
		
		JLabel lblNewLabel = new JLabel("序号/总数：");
		lblNewLabel.setFont(new Font("华文细黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(50, 50, 200, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("学号：");
		lblNewLabel_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(50, 100, 100, 50);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("性别：");
		lblNewLabel_2.setFont(new Font("华文细黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(50, 200, 100, 50);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("姓名：");
		lblNewLabel_3.setFont(new Font("华文细黑", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(50, 150, 100, 50);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("班级：");
		lblNewLabel_4.setFont(new Font("华文细黑", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(50, 250, 100, 50);
		contentPane.add(lblNewLabel_4);
		
		 JLabel lblNewLabel_5 = new JLabel("提示栏");
		 lblNewLabel_5.setFont(new Font("华文细黑", Font.PLAIN, 16));
		 lblNewLabel_5.setBounds(250, 50, 200, 50);
		 contentPane.add(lblNewLabel_5);
		
		//end
		
		//button
		//start
		JButton btnNewButton = new JButton("|<");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				
				 n=0;
				 index=n+1;
				 lblNewLabel.setText("序号/总数："+index+"/"+count);
				 FindIterable<Document> document = mongoClient.getDatabase("myTest").getCollection("student").find().skip(0).limit(1);
			     document.forEach(new Block<Document>() {
						public void apply(Document doc) {
							textField.setText(doc.getString("_id"));
							textField_1.setText(doc.getString("name"));
							textField_2.setText(doc.getString("gender"));
							textField_3.setText(doc.getString("class"));
							 if(doc.getString("name")==null||doc.getString("gender")==null||doc.getString("classes")==null)
						     {
						    	 lblNewLabel_5.setText("信息有缺失！");	    	 
						     }
						     else {
						    	 lblNewLabel_5.setText("信息完整");	  
						     }
												
						}
					});		
				
			}
		});
		btnNewButton.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton.setBounds(450, 100, 100, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				if(n-1>=0) {			
					n--;
					index=n+1;
				 lblNewLabel.setText("序号/总数："+index+"/"+count);
				 FindIterable<Document> document = mongoClient.getDatabase("myTest").getCollection("student").find().skip(n).limit(1);
			     document.forEach(new Block<Document>() {
						public void apply(Document doc) {
							textField.setText(doc.getString("_id"));
							textField_1.setText(doc.getString("name"));
							textField_2.setText(doc.getString("gender"));
							textField_3.setText(doc.getString("class"));
							 if(doc.getString("name")==null||doc.getString("gender")==null||doc.getString("classes")==null)
						     {
						    	 lblNewLabel_5.setText("信息有缺失！");	    	 
						     }
						     else {
						    	 lblNewLabel_5.setText("信息完整");	  
						     }
												
						}
					});		
				}		
				else if(n-1<0)
				{
					lblNewLabel.setText("已经是第一条！");
				}
				
				
			}
		});
		btnNewButton_1.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton_1.setBounds(450, 150, 100, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton(">");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		


				if(n+1<=count-1)
				{
					n++;
					index=n+1;
				 lblNewLabel.setText("序号/总数："+index+"/"+count);
				 FindIterable<Document> document = mongoClient.getDatabase("myTest").getCollection("student").find().skip(n).limit(1);
			     document.forEach(new Block<Document>() {
						public void apply(Document doc) {
							textField.setText(doc.getString("_id"));
							textField_1.setText(doc.getString("name"));
							textField_2.setText(doc.getString("gender"));
							textField_3.setText(doc.getString("class"));
							 if(doc.getString("name")==null||doc.getString("gender")==null||doc.getString("classes")==null)
						     {
						    	 lblNewLabel_5.setText("信息有缺失！");	    	 
						     }
						     else {
						    	 lblNewLabel_5.setText("信息完整");	  
						     }
												
						}
					});	
				}
				else if(n+1>count-1)
				{
					lblNewLabel.setText("已经是最后一条！");
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton_2.setBounds(450, 200, 100, 50);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton(">|");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

				
				 n=count-1;
				 index=n+1;
				 lblNewLabel.setText("序号/总数："+index+"/"+count);
				 FindIterable<Document> document = mongoClient.getDatabase("myTest").getCollection("student").find().skip(count-1).limit(1);
			     document.forEach(new Block<Document>() {
						public void apply(Document doc) {
							textField.setText(doc.getString("_id"));
							textField_1.setText(doc.getString("name"));
							textField_2.setText(doc.getString("gender"));
							textField_3.setText(doc.getString("class"));
							 if(doc.getString("name")==null||doc.getString("gender")==null||doc.getString("classes")==null)
						     {
						    	 lblNewLabel_5.setText("信息有缺失！");	    	 
						     }
						     else {
						    	 lblNewLabel_5.setText("信息完整");	  
						     }
												
						}
					});		
				
			}
		});
		btnNewButton_3.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton_3.setBounds(450, 250, 100, 50);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("清除");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				
			}
		});
		btnNewButton_4.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton_4.setBounds(420, 350, 100, 50);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("保存");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				
				
				Student student=new Student();			
				student.setid(textField.getText());
				student.setname(textField_1.getText());
				student.setgender(textField_2.getText());
				student.setclasses(textField_3.getText());
				
				try {
					mongoClient.getDatabase("myTest").getCollection("student").
					updateOne(Filters.eq("_id", textField.getText()),new Document("$set", new Document("name", textField_1.getText())));
					mongoClient.getDatabase("myTest").getCollection("student").
					updateOne(Filters.eq("_id", textField.getText()),new Document("$set", new Document("gender", textField_2.getText())));
					mongoClient.getDatabase("myTest").getCollection("student").
					updateOne(Filters.eq("_id", textField.getText()),new Document("$set", new Document("classes", textField_3.getText())));
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		btnNewButton_5.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton_5.setBounds(200, 350, 100, 50);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("删除");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				  //申明删除条件
				  Bson filter = Filters.eq("_id",textField.getText());
				  //删除与筛选器匹配的单个文档
				  mongoClient.getDatabase("myTest").getCollection("student").deleteOne(filter);
				
			}
		});
		btnNewButton_6.setFont(new Font("华文细黑", Font.PLAIN, 16));
		btnNewButton_6.setBounds(310, 350, 100, 50);
		contentPane.add(btnNewButton_6);
		
		//end
		
		//textfield
		//start
		textField = new JTextField();
		textField.setBounds(200, 100, 200, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(200, 150, 200, 50);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(200, 200, 200, 50);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(200, 250, 200, 50);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		//end

		 
		 count=(int)mongoClient.getDatabase("myTest").getCollection("student").count();
				 //countDocuments();	
		 n=0;
		 index=n+1;		 
		 lblNewLabel.setText("序号/总数："+index+"/"+count);
		 
		
		 FindIterable<Document> document = mongoClient.getDatabase("myTest").getCollection("student").find().skip(0).limit(1);
	     document.forEach(new Block<Document>() {
				public void apply(Document doc) {
					textField.setText(doc.getString("_id"));
					textField_1.setText(doc.getString("name"));
					textField_2.setText(doc.getString("gender"));
					textField_3.setText(doc.getString("classes"));
				     if(doc.getString("name")==null||doc.getString("gender")==null||doc.getString("classes")==null)
				     {
				    	 lblNewLabel_5.setText("信息有缺失！");	    	 
				     }
				     else {
				    	 lblNewLabel_5.setText("信息完整");	  
				     }
										
				}
			});		

	}
}
