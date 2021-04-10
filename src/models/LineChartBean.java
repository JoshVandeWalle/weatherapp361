package models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
@ViewScoped
public class LineChartBean {
  private LineChartModel lineModel;
  
  private List<Weather> data;
  
  public LineChartBean() {
	super();
}

public LineChartBean(List<Weather> data) {
	super();
	this.data = data;
	
	lineModel = new LineChartModel();
    LineChartSeries s = new LineChartSeries();
    s.setLabel("Weather");
    
    System.out.println("linechart con");
    for (int i = 0; i < data.size(); i++)
    {
    	System.out.println(Integer.parseInt(data.get(i).getDatetime().substring(11, 12)));
  	  s.set(Integer.parseInt(data.get(i).getDatetime().substring(11, 13)), data.get(i).getTemp());
    	//s.set(i, data.get(i).getTemp());
    }

    lineModel.addSeries(s);
    lineModel.setLegendPosition("e");
    Axis y = lineModel.getAxis(AxisType.Y);
    y.setMin(40);
    y.setMax(80);
    y.setTickInterval("5");
    y.setLabel("Degrees Farenheit");

    Axis x = lineModel.getAxis(AxisType.X);
    x.setMin(0);
    x.setMax(23);
    x.setTickInterval("1");
    x.setLabel("Time");
}

@PostConstruct
  public void init() {
	System.out.println("in post construct");
      

  }

  public LineChartModel getLineModel() {
      return lineModel;
  }
}
