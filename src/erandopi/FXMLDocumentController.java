/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import erandopi.techniques.DataSource;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jfree.chart.ChartFactory;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 *
 * @author student1
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private CategoryAxis X;

    @FXML
    private NumberAxis Y;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnExport;
    
      @FXML
    private JFXButton retour;

    @FXML
    void RetourAction(ActionEvent event) throws IOException {
changescene("/erandopi/gui/AdminProfile.fxml",event);

    }
    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loadChart(ActionEvent event) {
        String query = "select type,niveau FROM randonne ORDER BY niveau asc";
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            connection = DataSource.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            barChart.getData().add(series);

        } catch (Exception e) {
        }
    }

    private BarChart loadChart1() {
        String query = "select type,niveau FROM randonne ORDER BY niveau asc";
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            connection = DataSource.getInstance().getConnection();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
            barChart.getData().add(series);

        } catch (Exception e) {

        }
        return barChart;

    }

    @FXML
    private void exportPDF(ActionEvent event) throws IOException {
        writeChartToPDF(loadChart1() , 500, 400, "C:/Users/student1/Documents/NetBeansProjects/barchart.pdf");
                TrayNotification tray = new TrayNotification("Tres bien", "Vos fichiers PDF ont été exportés", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));

    }

     public void writeChartToPDF(BarChart chart, int width, int height, String fileName) {
         WritableImage i = new WritableImage(200, 200);
         chart.snapshot(new SnapshotParameters(), i);

    PdfWriter writer = null;
    Document document = new Document();
    try {
        writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(width, height);
        Graphics2D graphics2d = template.createGraphics(width, height,new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,height);
        graphics2d.dispose();
        contentByte.addTemplate(template, 0, 0);
    } catch (Exception e) {
        e.printStackTrace();
    }
    document.close();
}
    /*  public static BarChart generateBarChart() {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        serviceevaluation ev = new serviceevaluation();
        int i = ev.getinfcinq();
        int j = ev.getsupcinqinfdix();
        int k = ev.getsupdixinfquinze();
        int l = ev.getsupquinze();
        dataSet.setValue(i, "Note", "< 5");
        dataSet.setValue(j, "Note", "5<..<10");
        dataSet.setValue(k, "Note", "10<..<15");
        dataSet.setValue(l, "Note", ">15");
        BarChart chart = ChartFactory.createBarChart( "Statistique des Notes", "Note", "Nombre de Notes", dataSet, PlotOrientation.VERTICAL, false, true, false);
        return chart;
    }*/

  void changescene(String gui, ActionEvent event) throws IOException {
 FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

            Parent root1 = fxmlloder.load();
            Scene home_scene = new Scene(root1);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_scene);
            app_stage.show();
}  
}
