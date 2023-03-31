package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display extends JFrame implements ActionListener {
	private JFrame disp;
	private JLabel msg_lbl;
	private JPanel top_panel,mid_panel,bottom_Panel;
	private JLabel parent_lbl,parent_suit_lbl,parent_no_lbl;
	private JLabel child_lbl,child_suit_lbl,child_no_lbl;
	private JButton btn_high,btn_low;
	private Player parent,child;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        Deck deck = new Deck();
			        
			        Player parent = new Player();
			        parent.Draw(deck);
			        
			        Player child = new Player();
			        child.Draw(deck);
			        
					Display frame = new Display(parent, child);
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
//	/**
//	 * Initialize the contents of the frame.
//	 */
	public Display (Player prn, Player chl)
	{
		parent = prn;
		child = chl;
		
		disp = new JFrame("HIGH AND LOW");
		disp.setSize(432,457);
		disp.setLocationRelativeTo(null);
		disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		disp.setResizable(false);
		
		top_panel = new JPanel();
		setPanel(top_panel,Color.ORANGE,null,new Dimension(400,50));
		disp.getContentPane().add(top_panel,BorderLayout.NORTH);
		
		msg_lbl = new JLabel("一発勝負");
		top_panel.add(msg_lbl);
		setLabelFont(msg_lbl,Color.BLACK,0,15,480,20,20,false);
		
		mid_panel = new JPanel();
		setPanel(mid_panel,Color.CYAN,null,new Dimension(480,180));
		disp.getContentPane().add(mid_panel,BorderLayout.CENTER);
		
		parent_lbl = new JLabel("私のカード");
		parent_suit_lbl = new JLabel(getSuitIcon(parent.GetSuit()));
		parent_no_lbl = new JLabel(getNoStr(parent.GetNo()));
		
		mid_panel.add(parent_lbl);
		mid_panel.add(parent_suit_lbl);
		mid_panel.add(parent_no_lbl);
		
		setLabelFont(parent_lbl,Color.WHITE,90,10,100,20,14,false);
		setLabelFont(parent_suit_lbl,Color.WHITE,100,10,80,100,16,false);
		setLabelFont(parent_suit_lbl,Color.WHITE,100,35,80,100,16,true);
		
		child_lbl = new JLabel("あなたのカード");
		child_suit_lbl = new JLabel("");
		child_no_lbl = new JLabel("?");
		
		mid_panel.add(child_lbl);
		mid_panel.add(child_suit_lbl);
		mid_panel.add(child_no_lbl);

		setLabelFont(child_lbl,Color.WHITE,265,10,150,20,14,false);
		setLabelFont(child_suit_lbl,Color.LIGHT_GRAY,300,10,80,100,16,false);
		setLabelFont(child_suit_lbl,Color.LIGHT_GRAY,300,35,80,100,16,true);
		
		bottom_Panel = new JPanel();
		setPanel(bottom_Panel,Color.LIGHT_GRAY,new BorderLayout(),new Dimension(480,50));
		disp.getContentPane().add(bottom_Panel,BorderLayout.SOUTH);
		
		bottom_Panel = new JPanel();
		setPanel(bottom_Panel,Color.LIGHT_GRAY,new BorderLayout(),new Dimension(480,50));
		disp.add(bottom_Panel,BorderLayout.SOUTH);
		
		btn_high = new JButton("HIGHT");
		setButton(btn_high,this,240,50,20);
		bottom_Panel.add(btn_high,BorderLayout.WEST);
		
		btn_low = new JButton("LOW");
		setButton(btn_low,this,240,50,20);
		bottom_Panel.add(btn_low,BorderLayout.EAST);
		
		disp.setVisible(true);
	}
		
		public void actionPerformed(ActionEvent e)
		{
			String cmd = e.getActionCommand();
			int parent_no = parent.GetNo();
			int child_no = child.GetNo();
			int child_suit = child.GetSuit();
			
			child_no_lbl.setBackground(Color.WHITE);
			child_no_lbl.setText(getNoStr(child.GetNo()));
			child_suit_lbl.setBackground(Color.WHITE);
			child_suit_lbl.setIcon(getSuitIcon(child_suit));
			
			if(cmd.equals("HIGH"))
			{
				btn_high.setBackground(Color.GREEN);
				
				if(parent_no < child_no)
					msg_lbl.setText("あなたの勝ち");
				else if(child_no < parent_no)
					msg_lbl.setText("あなたの負け");
				else
					msg_lbl.setText("引き分け");
			}
			else if(cmd.equals("LOW"))
			{
				btn_low.setBackground(Color.GREEN);
				
				if(parent_no < child_no)
					msg_lbl.setText("あなたの負け");
				else if(parent_no > child_no)
						msg_lbl.setText("あなたの勝ち");
				else
					msg_lbl.setText("引き分け");
			}
			return;
		}
		
		public static void setPanel(JPanel panel, Color color, BorderLayout layout, Dimension dimension)
		{
			panel.setBackground(color);
			panel.setLayout(layout);
			panel.setPreferredSize(dimension);
			
			return;
		}
		
		public static void setLabelFont(JLabel label, Color clr, int x_pos, int y_pos, int x_size, int y_size, int strSize, boolean opq)
		{
			label.setBackground(clr);
			label.setLocation(x_pos,y_pos);
			label.setSize(x_size,y_size);
			label.setFont(new Font("MS ゴシック",Font.PLAIN,strSize));
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.CENTER);
			label.setOpaque(opq);
			
			return;
		}
		
		public static void setButton(JButton btn, ActionListener al, int x_size, int y_size, int strSize)
		{
			btn.setPreferredSize(new Dimension(x_size,y_size));
			btn.setFont(new Font("MS ゴシック",Font.PLAIN,strSize));
			btn.addActionListener(al);
			
			return;
		}
		
		public static ImageIcon getSuitIcon(int suit)
		{
			ImageIcon icon;
			
			switch(suit)
			{
			case 0:
				icon = new ImageIcon("/Users/kantanakagawa/java_workspace/Game1/src/highandlow/spade.jpg");
				return icon;
				
			case 1:
				icon = new ImageIcon("/Users/kantanakagawa/java_workspace/Game1/src/highandlow/heart.jpg");
				return icon;
			
			case 2:
				icon = new ImageIcon("/Users/kantanakagawa/java_workspace/Game1/src/highandlow/diamond.jpg");
				return icon;
				
			case 3:
				icon = new ImageIcon("/Users/kantanakagawa/java_workspace/Game1/src/highandlow/clover.jpg");
				return icon;
				
			default:
				return null;
			}
		}
		
		public static String getNoStr(int no)
		{
			switch(no)
			{
			case 1:
				return "A";
					
			case 11:
				return "J";
				
			case 12:
				return "Q";
				
			case 13:
				return "K";
				
			default:
				return String.valueOf(no);
			}
		}
	}
