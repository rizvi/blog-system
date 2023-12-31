package com.mit.du.blogger.BloggerPages;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import com.mit.du.common.DBUtil;
import com.mit.du.common.tableview.ManagerCheckOutTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerCheckOutDetails implements Initializable {

    public TableView<ManagerCheckOutTable> checkInoutTable;
    public TableColumn<ManagerCheckOutTable, String> nidCol;
    public TableColumn<ManagerCheckOutTable, String> roomNoCol;
    public TableColumn<ManagerCheckOutTable, String> roomTypeCol;
    public TableColumn<ManagerCheckOutTable, String> checkedInCol;
    public TableColumn<ManagerCheckOutTable, String> checkedOutCol;
    public TableColumn<ManagerCheckOutTable, String> priceDayCol;
    public TableColumn<ManagerCheckOutTable, String> totalPriceCol;
    public TableColumn<ManagerCheckOutTable, String> slipCol;

    private ObservableList<ManagerCheckOutTable> TABLEROW = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nidCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("nid"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("roomno"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("type"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("checkedin"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("checkedout"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("priceday"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("totalprice"));
        //slipCol.setCellValueFactory(new PropertyValueFactory<ManagerCheckOutTable, String>("nid"));

        showCheckInOutInfo();
        slipDownloadBtn();
    }

    public void showCheckInOutInfo() {
        TABLEROW.clear();
        Connection connection = DBUtil.getConnections();
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM CHECKINOUTINFO ORDER BY SI_NO DESC";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String ROOMNO = resultSet.getString("ROOMNO"); //SQL COL NAMES NID
                    String TYPE = resultSet.getString("ROOMTYPE");
                    String CAPACITY = resultSet.getString("CAPACITY");
                    String PRICEDAY = resultSet.getString("PRICEDAY");
                    String TOTALPRICE = resultSet.getString("TOTALPRICE");
                    String CHECKEDIN = resultSet.getString("CHECKEDIN");
                    String CHECKEDOUT = resultSet.getString("CHECKEDOUT");
                    String NID = resultSet.getString("NID");
                    ManagerCheckOutTable roomTablee = new ManagerCheckOutTable(NID, ROOMNO, TYPE, CAPACITY, PRICEDAY, TOTALPRICE, CHECKEDIN, CHECKEDOUT);

                    TABLEROW.add(roomTablee);
                }
                checkInoutTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnections();
        }
    }

    private void slipDownloadBtn() {
        Callback<TableColumn<ManagerCheckOutTable, String>, TableCell<ManagerCheckOutTable, String>> cellCallback
                = new Callback<TableColumn<ManagerCheckOutTable, String>, TableCell<ManagerCheckOutTable, String>>() {
            @Override
            public TableCell<ManagerCheckOutTable, String> call(TableColumn<ManagerCheckOutTable, String> param) {

                TableCell<ManagerCheckOutTable, String> cell = new TableCell<ManagerCheckOutTable, String>() {

                    FontAwesomeIconView downloadIcon = new FontAwesomeIconView(FontAwesomeIcon.DOWNLOAD);

                    public HBox hBox = new HBox(downloadIcon);

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            downloadIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                    + "-glyph-size:20px;"
                                    + "-fx-fill:#ffffff;"
                            );

                            downloadIcon.setOnMouseEntered((MouseEvent event) -> {
                                downloadIcon.setStyle(
                                        " -fx-cursor: hand ;"
                                        + "-glyph-size:20px;"
                                        + "-fx-fill:khaki;"
                                );
                            });

                            downloadIcon.setOnMouseExited((MouseEvent event2) -> {
                                downloadIcon.setStyle(
                                        " -fx-cursor: hand ;"
                                        + "-glyph-size:20px;"
                                        + "-fx-fill:white;"
                                );
                            });

                            downloadIcon.setOnMouseClicked((MouseEvent event2) -> {
                                downloadIcon.setStyle(
                                        " -fx-cursor: hand ;"
                                        + "-glyph-size:20px;"
                                        + "-fx-fill:lightgreen;"
                                );

                                //PDF generate function
                                ManagerCheckOutTable managerCheckOutTable = getTableView().getItems().get(getIndex());
                                try {
                                    genaratePdfSlip(managerCheckOutTable);
                                } catch (DocumentException | IOException e) {
                                    e.printStackTrace();
                                }

                            });

//                                    downloadIcon.setOnMouseClicked((MouseEvent event)->{
//
//                                    });
                            hBox.setStyle("-fx-alignment:center");
                            hBox.setMaxWidth(40);
//                                    HBox.setMargin(download, new Insets(2, 7, 0, 2));
//                                    HBox.setMargin(download, new Insets(2, 2, 0, 7));
                            setGraphic(hBox);
                        }
                    }
                };

                return cell;
            }
        };
        slipCol.setCellFactory(cellCallback);
    }

    private void genaratePdfSlip(ManagerCheckOutTable managerCheckOutTable) throws IOException, DocumentException {

        File currentDirFile = new File("");
        String pathFinder = currentDirFile.getAbsolutePath();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathFinder + "/tempManagerSlip.pdf"));
        document.open();

        String title = "Blog Management System Slip\n\n";
        String nid = "Blogger NID: " + managerCheckOutTable.getNid() + "\n";
        String roomNo = "Course ID: " + managerCheckOutTable.getRoomno() + "\n";
        String checkedIn = "Enrollment Date: " + managerCheckOutTable.getCheckedin() + "\n";
        String checkedOut = "Finish Date: " + managerCheckOutTable.getCheckedout() + "\n";
        String priceDay = "Price per day: " + managerCheckOutTable.getPriceday() + " Taka\n";
        String totalBill = "Total Bill: " + managerCheckOutTable.getTotalprice() + " Taka\n";
        String totalParagraph = title + nid + roomNo + checkedIn + checkedOut + priceDay + totalBill;

        Paragraph para = new Paragraph(totalParagraph);

        document.add(para);
        document.close();

        File file = new File(pathFinder + "/tempManagerSlip.pdf");
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("File Doesn't Exists");
        }
    }

}
