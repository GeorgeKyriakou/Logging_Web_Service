/**
 * The chart is created using the jfreechart library, and through an example that 
 * is provided by http://dirtyhandsphp.blogspot.gr/2012/07/example-to-draw-time-series-line-chart.html
 * ,Posted by Shiv Modi.
 * 
 * The values that make up the dataset were adjusted by Kyriakou George, Angelos
 * Papangelis and Luca Romano Pinder to fit the needs of a school assignment at
 * Malmo University, Sweden 29/12/2016.
 */

package zJimCarrey;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Stroke;
import java.text.SimpleDateFormat;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class motionDetectedChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * A demo.
	 *
	 * @param title
	 *            the frame title.
	 */
	public motionDetectedChart(final String title, String[] time, String[] date) {

		super(title);
		final XYDataset dataset = createDataset(time, date);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
		chartPanel.setMouseZoomable(true, false);
		this.add(chartPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	
	@SuppressWarnings("deprecation")
	private JFreeChart createChart(final XYDataset dataset) {

		final JFreeChart chart = ChartFactory.createTimeSeriesChart("Motion Detection Chart", "Date", "Detected",
				dataset, true, true, false);

		chart.setBackgroundPaint(Color.LIGHT_GRAY);

		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(new Color(0xffffe0));
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.lightGray);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.lightGray);
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(false);

		final XYItemRenderer xyitemrenderer = plot.getRenderer();
		if (xyitemrenderer instanceof XYLineAndShapeRenderer) {
			final XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) xyitemrenderer;
			renderer.setBaseShapesFilled(true);
			renderer.setBaseShapesVisible(true);
			renderer.setShapesVisible(true);
			renderer.setDrawOutlines(true);

			// sets the joint level size means the dot size which join two
			// points on a graph 2f,3f
			Stroke stroke = new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
			renderer.setBaseOutlineStroke(stroke);

		}

		final DateAxis xaxis = (DateAxis) plot.getDomainAxis();
		
		// Sets the Tick Labels means domain value labels by 90 degree
		xaxis.setVerticalTickLabels(true);

		// Try to play with it. It will work only in case of horizontal tick
		// mark
		xaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);

		// set date format
		xaxis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss MM-dd-yy"));

		final NumberAxis yaxis = (NumberAxis) plot.getRangeAxis();
		// To set range values integer only. Default are float
		yaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		return chart;

	}

	/**
	 * Creates a sample dataset.
	 *
	 * @return the dataset.
	 */
	@SuppressWarnings("deprecation")
	private XYDataset createDataset(String[] time, String[] date) {

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.setDomainIsPointsInTime(true);

		final TimeSeries s1 = new TimeSeries("Series 1", Second.class);

		// s1.add(new Second(0, 0, 0, 7, 7, 2012), 12);
		int[] hour = new int[time.length];
		int[] min = new int[time.length];
		int[] sec = new int[time.length];
		int[] day = new int[date.length];
		int[] month = new int[date.length];
		int[] year = new int[date.length];
		for (int i = 0; i < time.length; i++) {
			hour[i] = Integer.parseInt(time[i].substring(0, time[i].indexOf(":")));
			min[i] = Integer.parseInt(time[i].substring(3, 5));
			sec[i] = Integer.parseInt(time[i].substring(6, 8));
			year[i] = Integer.parseInt(date[i].substring(0, date[i].indexOf("-")));
			month[i] = Integer.parseInt(date[i].substring(5, 7));
			day[i] = Integer.parseInt(date[i].substring(8, 10));
			s1.addOrUpdate(new Second(sec[i], min[i], hour[i], day[i], month[i], year[i]), 1);

		}

		dataset.addSeries(s1);

		return dataset;
	}
}
