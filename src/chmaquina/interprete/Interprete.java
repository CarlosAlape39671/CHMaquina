/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmaquina.interprete;
import chmaquina.programa.Programa;
import chmaquina.sistemaOperativoGUI.SistemaOperativoIterfaz;

/**
 *
 * @author CAOS
 */
public class Interprete {
    private static Programa program;
    private static String[] line;
    private static String lineString;
    private static boolean screen;
    private static boolean printer;
    private static String dataToDisplayInScreen;
    private static String dataToDisplayInPrinter;
    private static boolean newPosition;
    private static int position;

    public static boolean getScreen() {
        return screen;
    }

    public static boolean getPrinter() {
        return printer;
    }

    public static boolean getNewPosition() {
        return newPosition;
    }

    public static String getDataToDisplayInScreen() {
        return dataToDisplayInScreen;
    }

    public static String getDataToDisplayInPrinter() {
        return dataToDisplayInPrinter;
    }

    public static int getPosition() {
        return position;
    }

    public static Programa getProgram() {
        return program;
    }

    public static String getLineString() {
        return lineString;
    }
    
    public static void setScreen(boolean screen) {
        Interprete.screen = screen;
    }

    public static void setPrinter(boolean printer) {
        Interprete.printer = printer;
    }

    public static void setProgram(Programa program) {
        Interprete.program = program;
    }

    public static void setLine(String[] line) {
        Interprete.line = line;
    }

    public static void setNewPosition(boolean newPosition) {
        Interprete.newPosition = newPosition;
    }    

    public static void setPosition(int position) {
        Interprete.position = position;
    }

    public static void establishLineString() {
        Interprete.lineString = "";
        for (int i = 0; i < line.length; i++) {
            Interprete.lineString += line[i] + " ";
        }
    }
    
    public static void interpret(){
        if (line[0].equals("lea") || line[0].equals("muestre")) {
            setScreen(true);
        } else {
            setScreen(false);
        }
        if (line[0].equals("imprima")) {
            setPrinter(true);
        } else {
            setPrinter(false);
        }
        if (line[0].equals("vaya") || line[0].equals("vayasi")) {
            setNewPosition(true);
        } else {
            setNewPosition(false);
        }
        
        if (line[0].equals("cargue")) {
            cargue(line[1]);
        }
        if (line[0].equals("almacene")) {
            almacene(line[1]);
        }
        if (line[0].equals("lea")) {
            setScreen(true);
            lea(line[1]);
        } else {
            setScreen(false);
        }
        if (line[0].equals("sume")) {
            sume(line[1]);
        }
        if (line[0].equals("reste")) {
            reste(line[1]);
        }
        if (line[0].equals("multiplique")) {
            multiplique(line[1]);
        }
        if (line[0].equals("divida")) {
            divida(line[1]);
        }
        if (line[0].equals("potencia")) {
            potencia(line[1]);
        }
        if (line[0].equals("modulo")) {
            modulo(line[1]);
        }
        if (line[0].equals("concatene")) {
            concatene(line[1]);
        }
        if (line[0].equals("elimine")) {
            elimine(line[1]);
        }
        if (line[0].equals("extraiga")) {
            extraiga(line[1]);
        }
        if (line[0].equals("Y")) {
            Y(line[1], line[2], line[3]);
        }
        if (line[0].equals("O")) {
            O(line[1], line[2], line[3]);
        }
        if (line[0].equals("NO")) {
            NO(line[1], line[2]);
        }
        if (line[0].equals("muestre")) {
            setScreen(true);
            muestre(line[1]);
        } else {
            setScreen(false);
        }
        if (line[0].equals("imprima")) {
            setPrinter(true);
            imprima(line[1]);
        } else {
            setPrinter(false);
        }
        if (line[0].equals("vaya")) {
            setNewPosition(true);
            vaya(line[1]);
        } else {
            setNewPosition(false);
        }
        if (line[0].equals("vayasi")) {
            setNewPosition(true);
            vayasi(line[1], line[2]);
        } else {
            setNewPosition(false);
        }
    }
    
    public static void cargue(String data){
        int index = program.getListVariables().indexOf(data);
        program.setAccumulator(program.getListVariablesValues().get(index));
    }    
    
    public static void almacene(String data){
        int index = program.getListVariables().indexOf(data);
        String accumulator = program.getAccumulator();
        program.getListVariablesValues().set(index, accumulator); 
    }    
    
    public static void lea(String data){
        int index = program.getListVariables().indexOf(data);
        String input = SistemaOperativoIterfaz.read();
        program.getListVariablesValues().set(index, input);         
        dataToDisplayInScreen = program.getListVariablesValues().get(index);
    }    
    
    public static void sume(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        if (program.isNumeric(accumulator)) {
            accumulator = String.valueOf(Integer.parseInt(accumulator) + Integer.parseInt(valueOfVariable));   
            program.setAccumulator(accumulator);
        }else {
            if (program.isDouble(accumulator)) {  
                accumulator = String.valueOf(Double.parseDouble(accumulator) + Double.parseDouble(valueOfVariable));      
                program.setAccumulator(accumulator);
            }
        }
    }    
    
    public static void reste(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        if (program.isNumeric(accumulator)) {
            accumulator = String.valueOf(Integer.parseInt(accumulator) - Integer.parseInt(valueOfVariable));   
            program.setAccumulator(accumulator);
        }else {
            if (program.isDouble(accumulator)) {  
                accumulator = String.valueOf(Double.parseDouble(accumulator) - Double.parseDouble(valueOfVariable));      
                program.setAccumulator(accumulator);
            }
        }
    }    
    
    public static void multiplique(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        if (program.isNumeric(accumulator)) {
            accumulator = String.valueOf(Integer.parseInt(accumulator) * Integer.parseInt(valueOfVariable));   
            program.setAccumulator(accumulator);
        }else {
            if (program.isDouble(accumulator)) {  
                accumulator = String.valueOf(Double.parseDouble(accumulator) * Double.parseDouble(valueOfVariable));      
                program.setAccumulator(accumulator);
            }
        }
    }    
    
    public static void divida(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        if (program.isNumeric(accumulator)) {
            accumulator = String.valueOf(Integer.parseInt(accumulator) / Integer.parseInt(valueOfVariable));   
            program.setAccumulator(accumulator);
        }else {
            if (program.isDouble(accumulator)) {  
                accumulator = String.valueOf(Double.parseDouble(accumulator) / Double.parseDouble(valueOfVariable));      
                program.setAccumulator(accumulator);
            }
        }
    }    
    
    public static void potencia(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        if (program.isNumeric(accumulator)) {
            accumulator = String.valueOf(Math.pow(Integer.parseInt(accumulator), Integer.parseInt(valueOfVariable)));   
            program.setAccumulator(accumulator);
        }else {
            if (program.isDouble(accumulator)) {  
                accumulator = String.valueOf(Math.pow(Double.parseDouble(accumulator), Integer.parseInt(valueOfVariable)));
                program.setAccumulator(accumulator);
            }
        }
    }    
    
    public static void modulo(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        if (program.isNumeric(accumulator)) {
            accumulator = String.valueOf(Integer.parseInt(accumulator) % Integer.parseInt(valueOfVariable));   
            program.setAccumulator(accumulator);
        }else {
            if (program.isDouble(accumulator)) {  
                accumulator = String.valueOf(Double.parseDouble(accumulator) % Double.parseDouble(valueOfVariable));
                program.setAccumulator(accumulator);
            }
        }
    }    
    
    public static void concatene(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        accumulator = accumulator + "" + valueOfVariable;
        program.setAccumulator(accumulator);
    }    
    
    public static void elimine(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        accumulator = accumulator.trim().replaceAll(valueOfVariable, " ");
        program.setAccumulator(accumulator);
    }    
    
    public static void extraiga(String data){
        String accumulator = program.getAccumulator();
        String valueOfVariable = getValueOfVariable(data);
        accumulator = accumulator.trim().replaceFirst(valueOfVariable, " ");
        program.setAccumulator(accumulator);
    }    
    
    public static void Y(String variableA, String variableB, String result){
        int valueOfVariableA = Integer.parseInt(getValueOfVariable(variableA));
        int valueOfVariableB = Integer.parseInt(getValueOfVariable(variableB));
        boolean valueOfResultBoolean = integerToBoolean(valueOfVariableA) && integerToBoolean(valueOfVariableB);
        String valueOfResult = String.valueOf(booleanToInteger(valueOfResultBoolean));
        int index = program.getListVariables().indexOf(result);
        program.getListVariablesValues().set(index, valueOfResult);
    }    
    
    public static void O(String variableA, String variableB, String result){
        int valueOfVariableA = Integer.parseInt(getValueOfVariable(variableA));
        int valueOfVariableB = Integer.parseInt(getValueOfVariable(variableB));
        boolean valueOfResultBoolean = integerToBoolean(valueOfVariableA) || integerToBoolean(valueOfVariableB);
        String valueOfResult = String.valueOf(booleanToInteger(valueOfResultBoolean));
        int index = program.getListVariables().indexOf(result);
        program.getListVariablesValues().set(index, valueOfResult);
    }    
    
    public static void NO(String variableA, String result){
        int valueOfVariableA = Integer.parseInt(getValueOfVariable(variableA));
        boolean valueOfResultBoolean = !integerToBoolean(valueOfVariableA);
        String valueOfResult = String.valueOf(booleanToInteger(valueOfResultBoolean));
        int index = program.getListVariables().indexOf(result);
        program.getListVariablesValues().set(index, valueOfResult);
    }    
    
    public static void muestre(String data){
        int index = program.getListVariables().indexOf(data);
        dataToDisplayInScreen = program.getListVariablesValues().get(index);
    }    
    
    public static void imprima(String data){
        int index = program.getListVariables().indexOf(data);
        dataToDisplayInPrinter = program.getListVariablesValues().get(index);
    }    
    
    public static void vaya(String data){
        position = Integer.parseInt(data);
        String accumulator = program.getAccumulator();
        double accumuladorInDouble = Double.parseDouble(accumulator);
        if (accumuladorInDouble == 0) {
            setNewPosition(false);
        }
    }    
    
    public static void vayasi(String tagA, String tagB){
        String accumulator = program.getAccumulator();
        int indexTagA = program.getListTags().indexOf(tagA);
        int indexTagB = program.getListTags().indexOf(tagB);
        int tagAValue = Integer.parseInt(program.getListTagsValues().get(indexTagA));
        int tagBValue = Integer.parseInt(program.getListTagsValues().get(indexTagB));
        if (program.isDouble(accumulator)) {
            double accumuladorInDouble = Double.parseDouble(accumulator);
            if (accumuladorInDouble > 0) {
                setPosition(tagAValue);
            }
            if (accumuladorInDouble < 0) {
                setPosition(tagBValue);
            }
            if (accumuladorInDouble == 0) {
                setNewPosition(false);
            }
        }
    }    
    
    public static String getValueOfVariable(String data) {
        int index = program.getListVariables().indexOf(data);
        String valueOfVariable = program.getListVariablesValues().get(index);
        return valueOfVariable;
    }
    
    public static boolean integerToBoolean(int valor){
        boolean p = false;
        if (valor == 1) {
            p = true;
        }
        return p;
    }    
    
    public static int booleanToInteger(boolean valor){
        int p = 0;
        if (valor == true) {
            p = 1;
        }
        return p;
    }    
    
    public static void rebootInterpreteVariables(){
        program = null;
        line = new String[0];
        lineString = "";
        screen = false;
        printer = false;
        dataToDisplayInScreen = "";
        dataToDisplayInPrinter = "";
        newPosition = false;
        position = 0;
    }
}