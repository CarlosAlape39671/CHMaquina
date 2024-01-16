/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmaquina.programa;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CAOS
 */
public class Programa {
    private ArrayList<String> wordsReserved = new ArrayList<String>();
    private ArrayList<String> chProgram = new ArrayList<String>();
    private ArrayList<String> listTags = new ArrayList<String>();
    private ArrayList<String> listTagsValues = new ArrayList<String>();
    private ArrayList<String> listVariables = new ArrayList<String>();
    private ArrayList<String> listVariablesType = new ArrayList<String>();
    private ArrayList<String> listVariablesValues = new ArrayList<String>();
    private boolean syntax = true;
    private String name = "";
    private String accumulator = "0";
    
    public Programa(String fileName, ArrayList<String> chProgramchProgramFromStart) {
        name = fileName;
        chProgram = chProgramchProgramFromStart;
        wordsReserved = new ArrayList<>(Arrays.asList(
            "cargue",
            "almacene",
            "nueva",
            "lea",
            "sume",
            "reste",
            "multiplique",
            "divida",
            "potencia",
            "modulo",
            "concatene",
            "elimine",
            "extraiga",
            "Y",
            "O",
            "NO",
            "muestre",
            "imprima",
            "retorne",
            "vaya",
            "vayasi",
            "etiqueta",
            "C",
            "I",
            "R",
            "L",
            "//"
        ));
    }
    
    public boolean getSyntax() {
        return syntax;
    }

    public ArrayList<String> getListTags() {
        return listTags;
    }

    public ArrayList<String> getChProgram() {
        return chProgram;
    }   

    public ArrayList<String> getListTagsValues() {
        return listTagsValues;
    }

    public ArrayList<String> getListVariables() {
        return listVariables;
    }

    public ArrayList<String> getListVariablesType() {
        return listVariablesType;
    }

    public ArrayList<String> getListVariablesValues() {
        return listVariablesValues;
    }

    public String getName() {
        return name;
    }

    public String getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(String accumulator) {
        this.accumulator = accumulator;
    }
    
    public void removeEmptyLines(){
        for (int i = 0; i < chProgram.size(); i++) {
            if (chProgram.get(i).trim().length() == 0) {
                chProgram.remove(i);
            }
        }
    }
    
    public void checkSyntax(){
        boolean syntax = true;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < chProgram.size(); j++) {
                if (syntax) {
                    boolean syntaxByLine = byLine(chProgram.get(j), j, i);
                    if (syntaxByLine == false) {
                        syntax = false;
                    } 
                }
            }
        }
        this.syntax = syntax;
    }
    
    public boolean byLine(String line, int position, int tour){
        line = line.trim().replaceAll(" +", " ");
        String[] list = new String[ line.split(" ").length ];
        list = line.split(" ");
        if (list[0].equals("//")) {
            return true;
        } else {
            if (tour == 0) {
                return firstTour(list);
            } else {
                return secondTour(list, position);
            }
        }
    }
    
    public boolean firstTour(String[] list) {
        boolean areWordsCorrectForFirstTour = reservedWordsReviewForFirstTour(list);
        boolean areWordsCorrect = reservedWordsReview(list);
        boolean isTagValueCorrect = tagValueReview(list);
        boolean isTagRepeated = tagRepeated(list);
        boolean isVariableValueCorrect = variableValueReview(list);
        boolean isVariableRepeated = variableRepeated(list);
        if (list[0].equals("etiqueta") && isTagValueCorrect && !isTagRepeated && areWordsCorrect && areWordsCorrectForFirstTour) {
            saveTag(list);
            saveTagValue(list);
        }
        if (list[0].equals("nueva") && isVariableValueCorrect && !isVariableRepeated && areWordsCorrect && areWordsCorrectForFirstTour) {
            saveVariable(list);
            saveVariableType(list);
            saveVariableValue(list);
        }
        if (
                !areWordsCorrect ||
                !isTagValueCorrect || 
                isTagRepeated ||
                !isVariableValueCorrect ||
                isVariableRepeated 
                ) {
            return false;
        }
        return true;
    }
    
    public boolean secondTour(String[] list, int position) {
        boolean isSizeOfLineCorrect = checkSizeOfLine(list);
        boolean isVariableExisting = variableExist(list);
        boolean isTagExisting = tagExist(list);
        boolean isLatestInstructionCorrect = checkLatestInstruction(list, position);
        if (
                !isSizeOfLineCorrect ||
                !isLatestInstructionCorrect ||
                !isVariableExisting ||
                !isTagExisting                 
                ) {
            return false;
        }   
        return true;
    }
    
    public boolean reservedWordsReviewForFirstTour(String[] list){
        if (!list[0].equals("nueva") && !list[0].equals("etiqueta")) {
            return false;
        }
        return true;
    }
    
    public boolean reservedWordsReview(String[] list) {
        boolean firstColumnWords = false;
        boolean thirdColumnWords = false;
        if (list.length <= 2 || !list[0].equals("nueva")) {
            thirdColumnWords = true;
        }
        for (int i = 0; i < wordsReserved.size(); i++) {
            if (list[0].equals(wordsReserved.get(i))) {
                firstColumnWords = true;
            }
            if (list.length > 2 && list[0].equals("nueva") && list[2].equals(wordsReserved.get(i)) ) {
                thirdColumnWords = true;
            }
        }
        if (thirdColumnWords && firstColumnWords) {
            return true;
        }else{
            return false;
        }
    }
    
    public boolean tagValueReview(String[] list){
        if (list[0].equals("etiqueta")) {
            if (list.length != 3) {
                return false;
            }
            if (!isNumeric(list[2])) {
                return false;
            }   
            if (chProgram.size() < Integer.parseInt(list[2])) {
                return false;
            }
        }        
        return true;
    }
    
    public boolean tagRepeated(String[] list){
        if (list[0].equals("etiqueta") && listTags.indexOf(list[1]) != -1) {
            return true;
        }
        return false;
    }
    
    public void saveTag(String[] list){
        listTags.add(list[1]);
    }
    
    public void saveTagValue(String[] list){
        listTagsValues.add( list[2] );
    }
    
    public boolean tagExist(String[] list){
        if ( list[0].equals("vayasi") && ( listTags.indexOf(list[1]) == -1 || listTags.indexOf(list[2]) == -1 ) ) {
            return false;
        }
        if ( list[0].equals("vaya") && listTags.indexOf(list[1]) == -1 ) {
            return false;
        }
        return true;
    }
    
    public boolean variableValueReview(String[] list){
        if (list[0].equals("nueva")) {
            if (!list[2].equals("C") && (list.length < 3 || list.length > 4)) {
                return false;
            }
            if (list[2].equals("I") && list.length > 3) {
                if (!isNumeric(list[3])) {
                    return false;
                }   
            }
            if (list[2].equals("R") && list.length > 3) {
                if (!isDouble(list[3])) {
                    return false;
                }   
            }
            if (list[2].equals("L") && list.length > 3) {
                if (!(list[3].equals("1") || list[3].equals("0"))) {
                    return false;
                }   
            }
        }
        return true;
    }
    
    public boolean variableRepeated(String[] list){
        if (list[0].equals("nueva") && listVariables.indexOf(list[1]) != -1) {
            return true;
        }
        return false;
    }
    
    public void saveVariable(String[] list){
        listVariables.add(list[1]);
    }
    
    public void saveVariableType(String[] list){
        listVariablesType.add(list[2]);
    }
    
    public void saveVariableValue(String[] list){
        if (list[2].equals("I") && list.length > 3 ) {
            listVariablesValues.add(list[3]);
        }else{
            if (list[2].equals("I") && list.length == 3) {
                listVariablesValues.add("0");
            }
        }
        
        if (list[2].equals("R") && list.length > 3 ) {
            listVariablesValues.add(list[3]);
        }else{
            if (list[2].equals("R") && list.length == 3) {
                listVariablesValues.add("0");
            }
        }
        
        if (list[2].equals("L") && list.length > 3 ) {
            listVariablesValues.add(list[3]);
        }else{
            if (list[2].equals("L") && list.length == 3) {
                listVariablesValues.add("0");
            }
        }
        
        if (list[2].equals("C") && list.length > 3 ) {
            list = checkC(list);
            listVariablesValues.add(list[3]);
        }else{
            if (list[2].equals("C") && list.length == 3) {
                listVariablesValues.add(" ");
            }
        }
    }
    
    public boolean variableExist(String[] list){
        if ( 
                !list[0].equals("nueva") && 
                listVariables.indexOf(list[1]) == -1 && 
                !list[1].equals("acumulador") && 
                !list[0].equals("etiqueta") && 
                !list[0].equals("vayasi") &&
                !list[0].equals("retorne") &&
                !list[0].equals("vaya")) {
            return false;
        }
        if (
                (list[0].equals("Y") || list[0].equals("O") || list[0].equals("NO")) &&
                listVariables.indexOf(list[2]) == -1 &&
                !list[2].equals("acumulador")) {
            return false;
        }
        if (
                (list[0].equals("Y") || list[0].equals("O")) &&
                listVariables.indexOf(list[3]) == -1 &&
                !list[3].equals("acumulador")) {
            return false;
        }
        return true;
    }
    
    public boolean checkLatestInstruction(String[] list, int position){
        if (list[0].equals("retorne") && position != chProgram.size() - 1) {
            return false;   
        }
        return true;
    }
    
    public boolean isNumeric(String string) {
        boolean result;
        try {
            Integer.parseInt(string);
            result = true;
        } catch (NumberFormatException exception) {
            result = false;
        }
        return result;
    }
    
    public boolean isDouble(String string) {
        boolean result;
        try {
            Double.parseDouble(string);
            result = true;
        } catch (NumberFormatException exception) {
            result = false;
        }
        return result;
    }
    
    public String[] checkC(String[] list){    
        String[] newlist = new String[4];
        for (int i = 4; i < list.length; i++) {
            list[3] += " " + list[i];
            list[i] = null;
        }
        for (int i = 0; i < 4; i++) {
            newlist[i] = list[i];
        }
        return newlist;
    }
    
    public boolean checkSizeOfLine(String[] list){    
        if (!list[0].equals("nueva") && !list[0].equals("etiqueta")) {
            if (list[0].equals("vayasi") || list[0].equals("NO")) {
                if (list.length != 3) {
                    return false;
                }
            } else {
                if (list[0].equals("Y") || list[0].equals("O")) {
                    if (list.length != 4) {
                        return false;
                    }
                } else {
                    if (list.length != 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
