package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

//CPU 사용량 TextVer, GuiVer
public class MemoryView extends JFrame implements ActionListener {
	/** The time series data. */
    private TimeSeries series;
 
    /** The most recent value added. */
    private double lastValue = 100.0;
 
    /** Timer to refresh graph after every second */
    private Timer timer = new Timer(1000, this);
    
	private JPanel contentPane;
	private JTextField txtZ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					 UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
					MemoryView frame = new MemoryView();
					frame.setVisible(true);
					//X버튼시 종료
					frame.addWindowListener(new WindowAdapter(){
			            public void windowClosing(WindowEvent e) { 
			                    System.exit(0);
			            }
			    });
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemoryView() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		this.series = new TimeSeries("CPU usage", Millisecond.class);
		 
		//차트 및 시간정의
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
 
        timer.setInitialDelay(1000);
 
        //Sets background color of chart
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
 
 
        //Created Chartpanel for chart area
        final ChartPanel chartPanel = new ChartPanel(chart);
 
		// 패널
        //버튼을 담는 메뉴패널
		JPanel menu_panel = new JPanel();
		FlowLayout fl_menu_panel = (FlowLayout) menu_panel.getLayout();
		fl_menu_panel.setAlignment(FlowLayout.RIGHT);
		contentPane.add(menu_panel, BorderLayout.NORTH);
		
		JToggleButton TextPBtn = new JToggleButton("Basic");
		menu_panel.add(TextPBtn);
		TextPBtn.setSelected(true);
		
		JToggleButton GuiPBtn = new JToggleButton("Gui Mod");
		menu_panel.add(GuiPBtn);
		
		//내용을 보여주는 메인패널
		JPanel main_panel = new JPanel();
		contentPane.add(main_panel, BorderLayout.CENTER);
		CardLayout cl_main_panel = new CardLayout(0, 0);
		main_panel.setLayout(cl_main_panel);
		
		// Text
		JPanel textPanel = new JPanel();
		main_panel.add(textPanel, "textPanel");
		textPanel.setLayout(new BorderLayout());
		
		txtZ = new JTextField();
		
		textPanel.add(txtZ, BorderLayout.CENTER);
		//버튼클릭시 화면전환 후 버튼 초기화
		TextPBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl_main_panel.show(main_panel, "textPanel");
				
				GuiPBtn.setSelected(false);
			}
		});
		
		//GUI
		JPanel guiPanel = new JPanel();
		main_panel.add(guiPanel, "guiPanel");
		guiPanel.setLayout(new BorderLayout());
        
		guiPanel.add(chartPanel, BorderLayout.CENTER);
		
		//Sets the size of whole window (JPanel)
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
 
		GuiPBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cl_main_panel.show(main_panel, "guiPanel");
				timer.start();
				TextPBtn.setSelected(false);
				
			}
		});
		

	}
    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "",
            "Time",
            "%",
            dataset,
            true,
            true,
            false
        );
 
        final XYPlot plot = result.getXYPlot();
 
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
 
        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
 
        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
        
//        ValueAxis yaxis = plot.getRangeAxis();
        // y축 증가값변경을 위한 NumberAxis로 변환
        NumberAxis yaxis = (NumberAxis) plot.getRangeAxis();
        yaxis.setRange(0.0, 100.0);
        yaxis.setTickUnit(new NumberTickUnit(10.0));
 
        return result;
    }
    /**
     * Generates an random entry for a particular call
 made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
    public void actionPerformed(final ActionEvent e) {
 
//        final double factor = 0.9 + 0.2*Math.random();
//        this.lastValue = this.lastValue * factor;
 
        final Millisecond now = new Millisecond();
        Date today = new Date();
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

        try {
			this.series.add(new Millisecond(), getProcessCpuLoad());
			txtZ.setText("Current Time : " + time.format(today) + ", CPU usage : " + Double.toString(getProcessCpuLoad())+"%");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    }
 
    public static double getProcessCpuLoad() throws Exception {

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    // usually takes a couple of seconds before we get real values
	    if (value == -1.0)      return Double.NaN;
	    // returns a percentage value with 1 decimal point precision
	    return ((int)(value * 1000) / 10.0);
	}
 
}
