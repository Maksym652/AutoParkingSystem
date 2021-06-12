/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingSystem;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.*;

/**
 *
 * @author 1
 */
public class ParkingForm extends javax.swing.JFrame {

    /**
     * Creates new form ParkingForm
     */
    Parking parking;
    Vector<String> columnNames;

    public ParkingForm() {
        initComponents();
        parking = new Parking(1f, 20f, 600f, 100);
        this.pricePerHourField.setValue(1f);
        this.pricePerDayField.setValue(20f);
        this.pricePerMonthField.setValue(600f);
        this.placeCountField.setValue(100);
        columnNames = new Vector();
        columnNames.add("Ім'я власника");
        columnNames.add("Номер машини");
        columnNames.add("Початок");
        columnNames.add("Кінець");
        columnNames.add("Вартість");
        this.allPlacesCountLabel.setText("100");
        this.occupidePlacesCountLabel.setText(Integer.toString(parking.getCarsOnParkingNow().size()));
        this.parkingReportTable.setModel(new DefaultTableModel(getDataForTable(parking.getAllCars()), columnNames));
        Date currentDate = new Date();
        Date monthBefore = new Date(currentDate.getYear(), currentDate.getMonth() - 1, currentDate.getDate(), currentDate.getHours(), currentDate.getMinutes());
        this.setBeginDateForFiltration(monthBefore);
        this.setEndDateForFiltration(currentDate);
        this.setBeginDateForVisit(currentDate);
        this.setEndDateForVisit(currentDate);
        this.parkingReportTable.setDefaultEditor(Object.class, null);
        this.parkingReportTable.setAutoCreateRowSorter(true);
        this.ownerNameForFiltrationField.setModel(new DefaultComboBoxModel(getOwnersNames(parking.getAllCars())));
        this.carNumberForFiltrationField.setModel(new DefaultComboBoxModel(getCarNumbers(parking.getAllCars())));
        AutoCompleteDecorator.decorate(this.ownerNameForFiltrationField);
        AutoCompleteDecorator.decorate(this.carNumberForFiltrationField);
    }

    Date getBeginDateForFiltration() {
        return new Date((int) this.beginYear.getValue() - 1900, (int) this.beginMonth.getValue() - 1, (int) this.beginDay.getValue(), (int) this.beginHour.getValue(), (int) this.beginMinute.getValue());
    }

    Date getEndDateForFiltration() {
        return new Date((int) this.endYear.getValue() - 1900, (int) this.endMonth.getValue() - 1, (int) this.endDay.getValue(), (int) this.endHour.getValue(), (int) this.endMinute.getValue());
    }

    Date getBeginDateForVisit() {
        return new Date((int) this.beginVisitYear.getValue() - 1900, (int) this.beginVisitMonth.getValue() - 1, (int) this.beginVisitDay.getValue(), (int) this.beginVisitHour.getValue(), (int) this.beginVisitMinute.getValue());
    }

    Date getEndDateForVisit() {
        return new Date((int) this.endVisitYear.getValue() - 1900, (int) this.endVisitMonth.getValue() - 1, (int) this.endVisitDay.getValue(), (int) this.endVisitHour.getValue(), (int) this.endVisitMinute.getValue());
    }

    void setBeginDateForFiltration(Date date) {
        this.beginYear.setValue(date.getYear() + 1900);
        this.beginMonth.setValue(date.getMonth() + 1);
        this.beginDay.setValue(date.getDate());
        this.beginHour.setValue(date.getHours());
        this.beginMinute.setValue(date.getMinutes());
    }

    void setEndDateForFiltration(Date date) {
        this.endYear.setValue(date.getYear() + 1900);
        this.endMonth.setValue(date.getMonth() + 1);
        this.endDay.setValue(date.getDate());
        this.endHour.setValue(date.getHours());
        this.endMinute.setValue(date.getMinutes());
    }

    void setBeginDateForVisit(Date date) {
        this.beginVisitYear.setValue(date.getYear() + 1900);
        this.beginVisitMonth.setValue(date.getMonth() + 1);
        this.beginVisitDay.setValue(date.getDate());
        this.beginVisitHour.setValue(date.getHours());
        this.beginVisitMinute.setValue(date.getMinutes());
    }

    void setEndDateForVisit(Date date) {
        this.endVisitYear.setValue(date.getYear() + 1900);
        this.endVisitMonth.setValue(date.getMonth() + 1);
        this.endVisitDay.setValue(date.getDate());
        this.endVisitHour.setValue(date.getHours());
        this.endVisitMinute.setValue(date.getMinutes());
    }

    Vector<Vector<String>> getDataForTable(ArrayList<Car> cars) {
        Vector<Vector<String>> result = new Vector<Vector<String>>();
        Vector<String> row;
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH:mm");
        for (Car car : cars) {
            for (Visit visit : car.getVisits()) {
                row = new Vector<String>();
                row.add(car.ownerName);
                row.add(car.number);
                row.add(df.format(visit.getBegin()));
                if (visit.getEnd().equals(new Date())) {
                    row.add("");
                } else {
                    row.add(df.format(visit.getEnd()));
                }
                if (visit.getPrice() != 0) {
                    row.add(Float.toString(visit.getPrice()));
                } else {
                    row.add("---");
                }
                result.add(row);
            }
        }
        return result;
    }

    Vector<String> getOwnersNames(ArrayList<Car> cars)
    {
        Vector<String> owners = new Vector<String>();
        for(Car car:cars){
            if(!owners.contains(car.getOwnerName())){
                owners.add(car.getOwnerName());
            }
        }
        return owners;
    }
    
    Vector<String> getCarNumbers(ArrayList<Car> cars){
        Vector<String> numbers = new Vector<String>();
        for(Car car:cars){
            numbers.add(car.getNumber());
        }
        return numbers;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        filtrationPanel = new javax.swing.JPanel();
        allOrCurrentBox = new javax.swing.JComboBox<>();
        filtrationByOwner = new javax.swing.JCheckBox();
        filtrationByCar = new javax.swing.JCheckBox();
        filtrationByTimeInterval = new javax.swing.JCheckBox();
        beginDateForFiltration = new javax.swing.JPanel();
        beginDay = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        beginMonth = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        beginYear = new javax.swing.JSpinner();
        beginHour = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        beginMinute = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        endDateForFiltration = new javax.swing.JPanel();
        endDay = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        endMonth = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        endYear = new javax.swing.JSpinner();
        endHour = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        endMinute = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        applyFilterButton = new javax.swing.JButton();
        setLastMonthAsTimeInterval = new javax.swing.JButton();
        cancelFiltration = new javax.swing.JButton();
        ownerNameForFiltrationField = new javax.swing.JComboBox<>();
        carNumberForFiltrationField = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        parkingReportTable = new javax.swing.JTable();
        parkingCarPanel = new javax.swing.JPanel();
        parkCarButton = new javax.swing.JButton();
        parkedCarOwnerField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        parkedCarNumberField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        beginVisitDay = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        beginVisitMonth = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        beginVisitYear = new javax.swing.JSpinner();
        beginVisitHour = new javax.swing.JSpinner();
        jLabel27 = new javax.swing.JLabel();
        beginVisitMinute = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        carLeavedParkingNumber = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        endVisitDay = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        endVisitMonth = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        endVisitYear = new javax.swing.JSpinner();
        endVisitHour = new javax.swing.JSpinner();
        jLabel36 = new javax.swing.JLabel();
        endVisitMinute = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        carLeaveParkingButton = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pricePerHourField = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pricePerDayField = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pricePerMonthField = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        setPricesButton = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        setPlaceCountButton = new javax.swing.JButton();
        placeCountField = new javax.swing.JSpinner();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        allPlacesCountLabel = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        occupidePlacesCountLabel = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        filtrationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        filtrationPanel.setName("reportFilter"); // NOI18N

        allOrCurrentBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Всі записи", "Машини на парковці" }));
        allOrCurrentBox.setName("allOrCurrentCars"); // NOI18N

        filtrationByOwner.setText("Ім'я власника");
        filtrationByOwner.setActionCommand("");
        filtrationByOwner.setName("showReportForOwner"); // NOI18N

        filtrationByCar.setText("Номер машини");
        filtrationByCar.setActionCommand("carNumber");
        filtrationByCar.setName("showReportForCar"); // NOI18N

        filtrationByTimeInterval.setText("За інтервал часу:");
        filtrationByTimeInterval.setName("showReportForPeriod"); // NOI18N

        beginDateForFiltration.setName("beginForReport"); // NOI18N

        beginDay.setModel(new javax.swing.SpinnerNumberModel(4, 1, 31, 1));
        beginDay.setName("beginDay"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("/");
        jLabel1.setToolTipText("");

        beginMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        beginMonth.setName("month"); // NOI18N
        beginMonth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beginMonthStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("/");
        jLabel2.setToolTipText("");

        beginYear.setModel(new javax.swing.SpinnerNumberModel(2021, 1900, null, 1));
        beginYear.setName("year"); // NOI18N
        beginYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beginYearStateChanged(evt);
            }
        });

        beginHour.setModel(new javax.swing.SpinnerNumberModel(1, 1, 23, 1));
        beginHour.setName("hour"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText(":");
        jLabel3.setToolTipText("");

        beginMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        beginMinute.setName("minute"); // NOI18N

        javax.swing.GroupLayout beginDateForFiltrationLayout = new javax.swing.GroupLayout(beginDateForFiltration);
        beginDateForFiltration.setLayout(beginDateForFiltrationLayout);
        beginDateForFiltrationLayout.setHorizontalGroup(
            beginDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beginDateForFiltrationLayout.createSequentialGroup()
                .addComponent(beginDay, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(beginHour, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(beginMinute, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
        );
        beginDateForFiltrationLayout.setVerticalGroup(
            beginDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beginDateForFiltrationLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(beginDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beginMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beginDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(beginDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(beginMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(beginYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(beginHour))))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel4.setText("(дата у форматі dd/mm/yyyy, час у 24-год. форматі)");

        endDateForFiltration.setName("endForReport"); // NOI18N

        endDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        endDay.setName("day"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("/");
        jLabel7.setToolTipText("");

        endMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        endMonth.setName("month"); // NOI18N
        endMonth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endMonthStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("/");
        jLabel8.setToolTipText("");

        endYear.setModel(new javax.swing.SpinnerNumberModel(2021, 1900, null, 1));
        endYear.setName("year"); // NOI18N
        endYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endYearStateChanged(evt);
            }
        });

        endHour.setModel(new javax.swing.SpinnerNumberModel(1, 1, 23, 1));
        endHour.setName("hour"); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText(":");
        jLabel9.setToolTipText("");

        endMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        endMinute.setName("minute"); // NOI18N

        javax.swing.GroupLayout endDateForFiltrationLayout = new javax.swing.GroupLayout(endDateForFiltration);
        endDateForFiltration.setLayout(endDateForFiltrationLayout);
        endDateForFiltrationLayout.setHorizontalGroup(
            endDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endDateForFiltrationLayout.createSequentialGroup()
                .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(endHour, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(endMinute, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
        );
        endDateForFiltrationLayout.setVerticalGroup(
            endDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endDateForFiltrationLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(endDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(endDateForFiltrationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(endMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(endHour))))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Від:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("До:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel17.setText("Відображення:");

        applyFilterButton.setText("Застосувати фільтрацію");
        applyFilterButton.setName("applyChanges"); // NOI18N
        applyFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyFilterButtonActionPerformed(evt);
            }
        });

        setLastMonthAsTimeInterval.setText("За останній місяць");
        setLastMonthAsTimeInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setLastMonthAsTimeIntervalActionPerformed(evt);
            }
        });

        cancelFiltration.setText("Скинути фільтрацію");
        cancelFiltration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelFiltrationActionPerformed(evt);
            }
        });

        ownerNameForFiltrationField.setEditable(true);

        carNumberForFiltrationField.setEditable(true);

        javax.swing.GroupLayout filtrationPanelLayout = new javax.swing.GroupLayout(filtrationPanel);
        filtrationPanel.setLayout(filtrationPanelLayout);
        filtrationPanelLayout.setHorizontalGroup(
            filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filtrationPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filtrationPanelLayout.createSequentialGroup()
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(allOrCurrentBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filtrationByOwner)
                            .addComponent(ownerNameForFiltrationField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(filtrationPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(filtrationByCar)
                                .addGap(32, 32, 32))
                            .addGroup(filtrationPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(carNumberForFiltrationField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(setLastMonthAsTimeInterval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filtrationByTimeInterval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(filtrationPanelLayout.createSequentialGroup()
                        .addComponent(applyFilterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelFiltration, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beginDateForFiltration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateForFiltration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );
        filtrationPanelLayout.setVerticalGroup(
            filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtrationPanelLayout.createSequentialGroup()
                .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filtrationPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(filtrationPanelLayout.createSequentialGroup()
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(filtrationByOwner)
                                .addComponent(jLabel17)
                                .addComponent(filtrationByTimeInterval)
                                .addComponent(filtrationByCar))
                            .addComponent(beginDateForFiltration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(allOrCurrentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(setLastMonthAsTimeInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ownerNameForFiltrationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(carNumberForFiltrationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(endDateForFiltration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(filtrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(applyFilterButton)
                            .addComponent(cancelFiltration))))
                .addGap(8, 8, 8))
        );

        filtrationByOwner.getAccessibleContext().setAccessibleName("byOwnerName");

        parkingReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        parkingReportTable.setName("table"); // NOI18N
        parkingReportTable.getTableHeader().setResizingAllowed(false);
        parkingReportTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(parkingReportTable);

        parkingCarPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Припаркувати авто"));
        parkingCarPanel.setToolTipText("");
        parkingCarPanel.setName("parkingCar"); // NOI18N

        parkCarButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        parkCarButton.setText("Припаркувати");
        parkCarButton.setActionCommand("parkCar");
        parkCarButton.setName("parkCarButton"); // NOI18N
        parkCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parkCarButtonActionPerformed(evt);
            }
        });

        parkedCarOwnerField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        parkedCarOwnerField.setName("parkedCarOwnerName"); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Власник:");

        parkedCarNumberField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        parkedCarNumberField.setName("parkedCarOwnerNumber"); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Номер:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Дата і час:");

        jPanel6.setName("parkedCarBeginTime"); // NOI18N

        beginVisitDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        beginVisitDay.setName("day"); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("/");
        jLabel25.setToolTipText("");

        beginVisitMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        beginVisitMonth.setName("month"); // NOI18N
        beginVisitMonth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beginVisitMonthStateChanged(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("/");
        jLabel26.setToolTipText("");

        beginVisitYear.setModel(new javax.swing.SpinnerNumberModel(2021, 1900, null, 1));
        beginVisitYear.setName("year"); // NOI18N
        beginVisitYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                beginVisitYearStateChanged(evt);
            }
        });

        beginVisitHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        beginVisitHour.setName("hour"); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel27.setText(":");
        jLabel27.setToolTipText("");

        beginVisitMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        beginVisitMinute.setName("minute"); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(beginVisitDay, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginVisitMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginVisitYear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(beginVisitHour, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beginVisitMinute, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(beginVisitMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(beginVisitYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beginVisitHour)
                    .addComponent(beginVisitDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beginVisitMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout parkingCarPanelLayout = new javax.swing.GroupLayout(parkingCarPanel);
        parkingCarPanel.setLayout(parkingCarPanelLayout);
        parkingCarPanelLayout.setHorizontalGroup(
            parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parkingCarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parkingCarPanelLayout.createSequentialGroup()
                        .addGroup(parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(parkedCarOwnerField, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(parkedCarNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(parkingCarPanelLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(parkingCarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(parkCarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        parkingCarPanelLayout.setVerticalGroup(
            parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parkingCarPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(parkingCarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parkedCarOwnerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(parkedCarNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(parkingCarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(27, 27, 27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parkingCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parkCarButton))
        );

        parkCarButton.getAccessibleContext().setAccessibleName("parkCarButton");
        parkCarButton.getAccessibleContext().setAccessibleDescription("");
        parkedCarOwnerField.getAccessibleContext().setAccessibleName("");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Прибрати авто з парковки"));
        jPanel8.setName("removingCar"); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Номер:");

        carLeavedParkingNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        carLeavedParkingNumber.setName("removingCarNumber"); // NOI18N

        endVisitDay.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        endVisitDay.setName("day"); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("/");
        jLabel34.setToolTipText("");

        endVisitMonth.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        endVisitMonth.setName("month"); // NOI18N
        endVisitMonth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endVisitMonthStateChanged(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("/");
        jLabel35.setToolTipText("");

        endVisitYear.setModel(new javax.swing.SpinnerNumberModel(2021, 1900, null, 1));
        endVisitYear.setName("year"); // NOI18N
        endVisitYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endVisitYearStateChanged(evt);
            }
        });

        endVisitHour.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));
        endVisitHour.setName("hour"); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel36.setText(":");
        jLabel36.setToolTipText("");

        endVisitMinute.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        endVisitMinute.setName("minute"); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(endVisitDay, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endVisitMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endVisitYear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(endVisitHour, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endVisitMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(endVisitMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(endVisitYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endVisitHour)
                    .addComponent(endVisitDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endVisitMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Дата і час:");

        carLeaveParkingButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        carLeaveParkingButton.setText("Прибрати авто");
        carLeaveParkingButton.setActionCommand("leaveParking");
        carLeaveParkingButton.setName("leaveParkingButton"); // NOI18N
        carLeaveParkingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carLeaveParkingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(carLeavedParkingNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(carLeaveParkingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(carLeavedParkingNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addComponent(carLeaveParkingButton)
                .addGap(8, 8, 8))
        );

        carLeaveParkingButton.getAccessibleContext().setAccessibleName("leaveParkingButton");

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Ціни"));
        jPanel10.setName("PricesPannel"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Встановити ціни:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("За годину:");

        pricePerHourField.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.01f));
        pricePerHourField.setName("pricePerHourField"); // NOI18N
        pricePerHourField.setValue(0);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("₴");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("За день:");

        pricePerDayField.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.01f));
        pricePerDayField.setName("pricePerDayField"); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("₴");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("За місяць:");

        pricePerMonthField.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 0.01f));
        pricePerMonthField.setName("pricePerMonthField"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("₴");

        setPricesButton.setText("Змінити ціни");
        setPricesButton.setActionCommand("setPrices");
        setPricesButton.setName("setPricesButton"); // NOI18N
        setPricesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPricesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(pricePerHourField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15))
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(pricePerDayField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(pricePerMonthField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(setPricesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pricePerDayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(pricePerMonthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(setPricesButton)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pricePerHourField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15)))))
        );

        setPricesButton.getAccessibleContext().setAccessibleName("setPricesButton");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Місця"));

        setPlaceCountButton.setText("Встановити кількість");
        setPlaceCountButton.setName("setPlacesCountButton"); // NOI18N
        setPlaceCountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setPlaceCountButtonActionPerformed(evt);
            }
        });

        placeCountField.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        placeCountField.setName("placesField"); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel38.setText("Кількість місць:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Місця на парковці(зайнято/всього):");

        allPlacesCountLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        allPlacesCountLabel.setText("jLabel40");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("/");

        occupidePlacesCountLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        occupidePlacesCountLabel.setText("jLabel40");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(placeCountField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(setPlaceCountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(occupidePlacesCountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allPlacesCountLabel)))
                .addGap(37, 37, 37))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(placeCountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setPlaceCountButton)
                    .addComponent(allPlacesCountLabel)
                    .addComponent(jLabel41)
                    .addComponent(occupidePlacesCountLabel))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(parkingCarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(filtrationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtrationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(parkingCarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setPricesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPricesButtonActionPerformed
        parking.setPricePerHour((float) this.pricePerHourField.getValue());
        parking.setPricePerDay((float) this.pricePerDayField.getValue());
        parking.setPricePerMonth((float) this.pricePerMonthField.getValue());
    }//GEN-LAST:event_setPricesButtonActionPerformed

    private void setPlaceCountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setPlaceCountButtonActionPerformed
        parking.setCountOfPlaces((int) this.placeCountField.getValue());
        this.allPlacesCountLabel.setText(Integer.toString(parking.placesCount()));
    }//GEN-LAST:event_setPlaceCountButtonActionPerformed

    private void applyFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyFilterButtonActionPerformed
        ArrayList<Car> result = new ArrayList<Car>();
        if (allOrCurrentBox.getSelectedIndex() == 0) {
            result = parking.getAllCars();
        } else {
            result = parking.getCarsOnParkingNow();
        }
        if (this.filtrationByOwner.isSelected()) {
            result = parking.filtration((Car c) -> (c.getOwnerName().equals(this.ownerNameForFiltrationField.getSelectedItem().toString().trim())), result);
        }
        if (this.filtrationByCar.isSelected()) {
            result = parking.filtration((Car c) -> (c.getNumber().equals(this.carNumberForFiltrationField.getSelectedItem().toString().trim())), result);
        }
        if (this.filtrationByTimeInterval.isSelected()) {
            if (this.getBeginDateForFiltration().after(this.getEndDateForFiltration())) {
                JOptionPane.showMessageDialog(this, "Початкова дата не може бути раніше за кінцеву!");
            } else {
                for (Car car : result) {
                    car.visits = car.visitsForPeriod(this.getBeginDateForFiltration(), this.getEndDateForFiltration());
                }
            }
        }
        this.parkingReportTable.setModel(new DefaultTableModel(getDataForTable(result), columnNames));
    }//GEN-LAST:event_applyFilterButtonActionPerformed

    private void cancelFiltrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelFiltrationActionPerformed
        this.parkingReportTable.setModel(new DefaultTableModel(getDataForTable(parking.getAllCars()), columnNames));
    }//GEN-LAST:event_cancelFiltrationActionPerformed

    private void setLastMonthAsTimeIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setLastMonthAsTimeIntervalActionPerformed
        Date currentDate = new Date();
        Date monthBefore = new Date(currentDate.getYear(), currentDate.getMonth() - 1, currentDate.getDate(), currentDate.getHours(), currentDate.getMinutes());
        this.setBeginDateForFiltration(monthBefore);
        this.setEndDateForFiltration(currentDate);
    }//GEN-LAST:event_setLastMonthAsTimeIntervalActionPerformed

    private void parkCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parkCarButtonActionPerformed
        if (!this.parkedCarNumberField.getText().matches("[A-Z0-9]+")) {
            JOptionPane.showMessageDialog(this, "Некоректний номер! Вводьте тільки великі літери латинського алфавіту та цифри.");
            return;
        }
        if (!parking.parkCar(this.parkedCarOwnerField.getText(), this.parkedCarNumberField.getText(), this.getBeginDateForVisit())) {
            JOptionPane.showMessageDialog(this, "Помилка! Можливо ця машина вже стоїть на парковці або місця на парковці закінчилися.");
        } else {
            this.ownerNameForFiltrationField.setModel(new DefaultComboBoxModel(getOwnersNames(parking.getAllCars())));
            this.carNumberForFiltrationField.setModel(new DefaultComboBoxModel(getCarNumbers(parking.getAllCars())));
            this.occupidePlacesCountLabel.setText(Integer.toString(parking.getCarsOnParkingNow().size()));
            this.parkingReportTable.setModel(new DefaultTableModel(getDataForTable(parking.getAllCars()), columnNames));
        }
    }//GEN-LAST:event_parkCarButtonActionPerformed

    private void carLeaveParkingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carLeaveParkingButtonActionPerformed
        if (!parking.leaveParking(this.carLeavedParkingNumber.getText(), this.getEndDateForVisit())) {
            JOptionPane.showMessageDialog(this, "Помилка! Ця машина відсутня на парковці або дата завершення стоянки вказана раніше ніж дата початку.");
        } else {
            this.occupidePlacesCountLabel.setText(Integer.toString(parking.getCarsOnParkingNow().size()));
            this.parkingReportTable.setModel(new DefaultTableModel(getDataForTable(parking.getAllCars()), columnNames));
        }
    }//GEN-LAST:event_carLeaveParkingButtonActionPerformed

    SpinnerNumberModel correctModelOfDate(SpinnerNumberModel model, int month, int year) {
        SpinnerNumberModel new_model = model;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                new_model.setMaximum(31);
                break;
            case 2:
                if (year % 4 == 0 && (year % 100 != 0 || (year % 100 == 0 && year % 400 == 0))) {
                    new_model.setMaximum(29);
                } else {
                    new_model.setMaximum(28);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                new_model.setMaximum(30);
                break;
        }
        if ((int) new_model.getValue() > (int) new_model.getMaximum()) {
            new_model.setValue(model.getMaximum());
        }
        return new_model;
    }

    private void beginMonthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_beginMonthStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.beginDay.getModel();
        this.beginDay.setModel(correctModelOfDate(model, (int) this.beginMonth.getValue(), (int) this.beginYear.getValue()));
    }//GEN-LAST:event_beginMonthStateChanged

    private void endMonthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endMonthStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.endDay.getModel();
        this.endDay.setModel(correctModelOfDate(model, (int) this.endMonth.getValue(), (int) this.endYear.getValue()));
    }//GEN-LAST:event_endMonthStateChanged

    private void beginVisitMonthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_beginVisitMonthStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.beginVisitDay.getModel();
        this.beginVisitDay.setModel(correctModelOfDate(model, (int) this.beginVisitMonth.getValue(), (int) this.beginVisitYear.getValue()));
    }//GEN-LAST:event_beginVisitMonthStateChanged

    private void endVisitMonthStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endVisitMonthStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.endVisitDay.getModel();
        this.endVisitDay.setModel(correctModelOfDate(model, (int) this.endVisitMonth.getValue(), (int) this.endVisitYear.getValue()));
    }//GEN-LAST:event_endVisitMonthStateChanged

    private void beginYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_beginYearStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.beginDay.getModel();
        this.beginDay.setModel(correctModelOfDate(model, (int) this.beginMonth.getValue(), (int) this.beginYear.getValue()));
    }//GEN-LAST:event_beginYearStateChanged

    private void endYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endYearStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.endDay.getModel();
        this.endDay.setModel(correctModelOfDate(model, (int) this.endMonth.getValue(), (int) this.endYear.getValue()));
    }//GEN-LAST:event_endYearStateChanged

    private void beginVisitYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_beginVisitYearStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.beginVisitDay.getModel();
        this.beginVisitDay.setModel(correctModelOfDate(model, (int) this.beginVisitMonth.getValue(), (int) this.beginVisitYear.getValue()));
    }//GEN-LAST:event_beginVisitYearStateChanged

    private void endVisitYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endVisitYearStateChanged
        SpinnerNumberModel model = (SpinnerNumberModel) this.endVisitDay.getModel();
        this.endVisitDay.setModel(correctModelOfDate(model, (int) this.endVisitMonth.getValue(), (int) this.endVisitYear.getValue()));
    }//GEN-LAST:event_endVisitYearStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParkingForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> allOrCurrentBox;
    private javax.swing.JLabel allPlacesCountLabel;
    private javax.swing.JButton applyFilterButton;
    private javax.swing.JPanel beginDateForFiltration;
    private javax.swing.JSpinner beginDay;
    private javax.swing.JSpinner beginHour;
    private javax.swing.JSpinner beginMinute;
    private javax.swing.JSpinner beginMonth;
    private javax.swing.JSpinner beginVisitDay;
    private javax.swing.JSpinner beginVisitHour;
    private javax.swing.JSpinner beginVisitMinute;
    private javax.swing.JSpinner beginVisitMonth;
    private javax.swing.JSpinner beginVisitYear;
    private javax.swing.JSpinner beginYear;
    private javax.swing.JButton cancelFiltration;
    private javax.swing.JButton carLeaveParkingButton;
    private javax.swing.JTextField carLeavedParkingNumber;
    private javax.swing.JComboBox<String> carNumberForFiltrationField;
    private javax.swing.JPanel endDateForFiltration;
    private javax.swing.JSpinner endDay;
    private javax.swing.JSpinner endHour;
    private javax.swing.JSpinner endMinute;
    private javax.swing.JSpinner endMonth;
    private javax.swing.JSpinner endVisitDay;
    private javax.swing.JSpinner endVisitHour;
    private javax.swing.JSpinner endVisitMinute;
    private javax.swing.JSpinner endVisitMonth;
    private javax.swing.JSpinner endVisitYear;
    private javax.swing.JSpinner endYear;
    private javax.swing.JCheckBox filtrationByCar;
    private javax.swing.JCheckBox filtrationByOwner;
    private javax.swing.JCheckBox filtrationByTimeInterval;
    private javax.swing.JPanel filtrationPanel;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel occupidePlacesCountLabel;
    private javax.swing.JComboBox<String> ownerNameForFiltrationField;
    private javax.swing.JButton parkCarButton;
    private javax.swing.JTextField parkedCarNumberField;
    private javax.swing.JTextField parkedCarOwnerField;
    private javax.swing.JPanel parkingCarPanel;
    private javax.swing.JTable parkingReportTable;
    private javax.swing.JSpinner placeCountField;
    private javax.swing.JSpinner pricePerDayField;
    private javax.swing.JSpinner pricePerHourField;
    private javax.swing.JSpinner pricePerMonthField;
    private javax.swing.JButton setLastMonthAsTimeInterval;
    private javax.swing.JButton setPlaceCountButton;
    private javax.swing.JButton setPricesButton;
    // End of variables declaration//GEN-END:variables
}
