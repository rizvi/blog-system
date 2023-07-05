package com.mit.du.zadmin.AdminPages;

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
import com.mit.du.backend.DBUtil;
import com.mit.du.backend.utils.AdminEarningTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminEarningLog implements Initializable {

    public TableView<AdminEarningTable> earningLogTable;
    public TableColumn<AdminEarningTable, String> nidCol;
    public TableColumn<AdminEarningTable, String> roomNoCol;
    public TableColumn<AdminEarningTable, String> roomTypeCol;
    public TableColumn<AdminEarningTable, String> checkedInCol;
    public TableColumn<AdminEarningTable, String> checkedOutCol;
    public TableColumn<AdminEarningTable, String> priceDayCol;
    public TableColumn<AdminEarningTable, String> totalPriceCol;
    public TableColumn<AdminEarningTable, String> slipCol;

    private ObservableList<AdminEarningTable> TABLEROW = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nidCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("nid"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("roomno"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("type"));
        checkedInCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("checkedin"));
        checkedOutCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("checkedout"));
        priceDayCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("priceday"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<AdminEarningTable, String>("totalprice"));
        showEarningLog();
        slipDownloadBtn();
    }

    public void showEarningLog() {
        TABLEROW.clear();
        Connection connection = DBUtil.getConnections();
        try {
            if (!connection.isClosed()) {
                String sql = "SELECT * FROM CHECKINOUTINFO WHERE CHECKEDOUT IS NOT NULL ORDER BY SI_NO DESC";
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
                    AdminEarningTable roomTablee = new AdminEarningTable(NID, ROOMNO, TYPE, CHECKEDIN, CHECKEDOUT, PRICEDAY, TOTALPRICE);

                    TABLEROW.add(roomTablee);
                }
                earningLogTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeConnections();
        }
    }

    private void slipDownloadBtn() {
        Callback<TableColumn<AdminEarningTable, String>, TableCell<AdminEarningTable, String>> cellCallback
                = new Callback<TableColumn<AdminEarningTable, String>, TableCell<AdminEarningTable, String>>() {
            @Override
            public TableCell<AdminEarningTable, String> call(TableColumn<AdminEarningTable, String> param) {

                TableCell<AdminEarningTable, String> cell = new TableCell<AdminEarningTable, String>() {

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
                                AdminEarningTable adminEarningTable = getTableView().getItems().get(getIndex());
                                try {
                                    genaratePdfSlip(adminEarningTable);
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

    private void genaratePdfSlip(AdminEarningTable adminEarningTable) throws IOException, DocumentException {

        File currentDirFile = new File("");
        String pathFinder = currentDirFile.getAbsolutePath();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pathFinder + "/tempAdminSlip.pdf"));
        document.open();

        String title = "Blog Management System Slip\n\n";
        String nid = "Admin NID: " + adminEarningTable.getNid() + "\n";
        String roomNo = "Room No: " + adminEarningTable.getRoomno() + "\n";
        String checkedIn = "Checked In Date: " + adminEarningTable.getCheckedin() + "\n";
        String checkedOut = "Checked Out Date: " + adminEarningTable.getCheckedout() + "\n";
        String priceDay = "Price per day: " + adminEarningTable.getPriceday() + " Taka\n";
        String totalBill = "Total Bill: " + adminEarningTable.getTotalprice() + " Taka\n";
        String totalParagraph = title + nid + roomNo + checkedIn + checkedOut + priceDay + totalBill;

        Paragraph para = new Paragraph(totalParagraph);

        document.add(para);
        document.close();

        File file = new File(pathFinder + "/tempAdminSlip.pdf");
        if (file.exists()) {
            Desktop.getDesktop().open(file);
        } else {
            System.out.println("File Doesn't Exists");
        }
    }

}
