/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmaquina.sistemaOperativo;
import chmaquina.programa.Programa;
import java.util.ArrayList;

/**
 *
 * @author CAOS
 */
public class SistemaOperativo {
    
    private static int kernelSize;
    private static int memorySize;
    private static int SizeMemoryOccupied = 0;
    private static ArrayList<Programa> programs = new ArrayList<Programa>();
    private static String[] memory;
    private static ArrayList<String> tags = new ArrayList<String>();
    private static ArrayList<Integer> positionTagsInMemory = new ArrayList<Integer>();
    private static ArrayList<String> variables = new ArrayList<String>();
    private static ArrayList<Integer> positionVariablesInMemory = new ArrayList<Integer>();
    private static ArrayList<String> processes = new ArrayList<String>();
    private static ArrayList<Integer> positionProcessesInMemory = new ArrayList<Integer>();
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<String> inss = new ArrayList<String>();
    private static ArrayList<String> rbs = new ArrayList<String>();
    private static ArrayList<String> rlcs = new ArrayList<String>();
    private static ArrayList<String> rlps = new ArrayList<String>();
    private static ArrayList<String> priorities = new ArrayList<String>();
    private static boolean screen;
    private static boolean printer;
    private static int NumberOfIterationsPerProgram;
    private static String typeOfPriority;
    private static int[] executedLines;
    private static int programIterator;
    private static int countLineIterator;
    private static boolean firstIterationStepByStep;
    private static ArrayList<String> valuesToPrint = new ArrayList<String>();

    public static String[] getMemory() {
        return memory;
    }

    public static ArrayList<String> getTags() {
        return tags;
    }

    public static ArrayList<Integer> getPositionTagsInMemory() {
        return positionTagsInMemory;
    }

    public static ArrayList<String> getVariables() {
        return variables;
    }

    public static ArrayList<Integer> getPositionVariablesInMemory() {
        return positionVariablesInMemory;
    }

    public static ArrayList<String> getProcesses() {
        return processes;
    }

    public static ArrayList<Integer> getPositionProcessesInMemory() {
        return positionProcessesInMemory;
    }

    public static ArrayList<String> getNames() {
        return names;
    }

    public static ArrayList<String> getInss() {
        return inss;
    }

    public static ArrayList<String> getRbs() {
        return rbs;
    }

    public static ArrayList<String> getRlcs() {
        return rlcs;
    }

    public static ArrayList<String> getRlps() {
        return rlps;
    }

    public static ArrayList<Programa> getPrograms() {
        return programs;
    }

    public static int getNumberOfIterationsPerProgram() {
        return NumberOfIterationsPerProgram;
    }

    public static String getTypeOfPriority() {
        return typeOfPriority;
    }

    public static int[] getExecutedLines() {
        return executedLines;
    }

    public static int getProgramIterator() {
        return programIterator;
    }

    public static int getCountLineIterator() {
        return countLineIterator;
    }

    public static ArrayList<String> getValuesToPrint() {
        return valuesToPrint;
    }

    public static boolean isFirstIterationStepByStep() {
        return firstIterationStepByStep;
    }
    
    public static void setPrograms(ArrayList<Programa> programs) {
        SistemaOperativo.programs = programs;
    }

    public static void setNumberOfIterationsPerProgram(int NumberOfIterationsPerProgram) {
        SistemaOperativo.NumberOfIterationsPerProgram = NumberOfIterationsPerProgram;
    }

    public static void setTypeOfPriority(String typeOfPriority) {
        SistemaOperativo.typeOfPriority = typeOfPriority;
    }

    public static void setProgramIterator(int programIterator) {
        SistemaOperativo.programIterator = programIterator;
    }
    
    public static void setCountLineIterator(int countLineIterator) {
        SistemaOperativo.countLineIterator = countLineIterator;
    }
    
    public static void setFirstIterationStepByStep(boolean firstIterationStepByStep) {
        SistemaOperativo.firstIterationStepByStep = firstIterationStepByStep;
    }

    public static void setValuesToPrint(ArrayList<String> valuesToPrint) {
        SistemaOperativo.valuesToPrint = valuesToPrint;
    }
    
    public static void SistemaOperativoKernelSizeAndMemorySize(int kernelSize, int memorySize) {
        SistemaOperativo.kernelSize = kernelSize;
        SistemaOperativo.memorySize = memorySize;
    }
    
    public static boolean saveProgram(Programa program){
        int sizeProgram = program.getChProgram().size();
        int sizeVariables = program.getListVariablesValues().size();
        int sizeComplete = SizeMemoryOccupied + sizeProgram + sizeVariables;
        if ( memorySize < sizeComplete ) {
            return false;
        }
        programs.add(program);
        return true;
    }
    
    public static void establishMemorySize() {
        memory = new String[memorySize];
        memory[0] = "0";
        SizeMemoryOccupied = 1;
        SizeMemoryOccupied += kernelSize;
    }
    
    public static void establishExecutedLinesSize() {
        executedLines = new int[programs.size()];
        for (int i = 0; i < programs.size(); i++) {
            executedLines[i] = 0;
        }
    }
    
    public static void establishData() {
        setMemoryData();
        setTagsData();
        setTagsIndex();
        setVariablesData();
        setVariablesIndex();
        setProcessesData();
        setProcessesIndex();
        setProgramsData();
    }
    
    public static void setMemoryData() {
        int position = programs.size() - 1;
        ArrayList<String> chProgram = programs.get(position).getChProgram();
        ArrayList<String> listVariablesValues = programs.get(position).getListVariablesValues();
        for (String chProgramLine : chProgram) {
            memory[SizeMemoryOccupied] = chProgramLine;
            SizeMemoryOccupied += 1;
        }
        for (String variableValue : listVariablesValues) {
            memory[SizeMemoryOccupied] = variableValue;
            SizeMemoryOccupied += 1;
        }
    }
    
    public static void updateMemoryData() {
        establishMemorySize();
        for (Programa program : programs) {
            ArrayList<String> chProgram = program.getChProgram();
            ArrayList<String> listVariablesValues = program.getListVariablesValues();
            for (String chProgramLine : chProgram) {
                memory[SizeMemoryOccupied] = chProgramLine;
                SizeMemoryOccupied += 1;
            }
            for (String variableValue : listVariablesValues) {
                memory[SizeMemoryOccupied] = variableValue;
                SizeMemoryOccupied += 1;
            }
        }
    }
    
    public static void setTagsData() {
        int position = programs.size() - 1;
        String positionFourDigits = checkAndChangePosition(position);
        ArrayList<String> listTags = programs.get(position).getListTags();
        for (String tag : listTags) {
            tags.add(positionFourDigits + tag);
        }
    }
    
    public static void setTagsIndex() {
        int position = programs.size() - 1;
        ArrayList<String> listTagsValues = programs.get(position).getListTagsValues();
        for (String tagValue : listTagsValues) {
            int positionInData = Integer.parseInt(tagValue);
            int positionInMemory = SistemaOperativo.positionInMemory(positionInData);
            positionTagsInMemory.add(positionInMemory);
        }
    }
    
    public static void setVariablesData() {
        int position = programs.size() - 1;
        String positionFourDigits = checkAndChangePosition(position);
        ArrayList<String> listVariables = programs.get(position).getListVariables();
        for (String variable : listVariables) {
            variables.add(positionFourDigits + variable);
        }
    }
    
    public static void setVariablesIndex() {
        int position = programs.size() - 1;
        ArrayList<String> listVariablesValues = programs.get(position).getListVariablesValues();
        int sizeChProgram = programs.get(position).getChProgram().size();
        for (int i = 0; i < listVariablesValues.size(); i++) {
            int positionInMemory = SistemaOperativo.positionInMemory(sizeChProgram + i + 1);
            positionVariablesInMemory.add(positionInMemory);
        }
    }
    
    public static void setProcessesData() {
        int position = programs.size() - 1;
        ArrayList<String> listProcesses = programs.get(position).getChProgram();
        for (String process : listProcesses) {
            processes.add(process);
        }
    }
    
    public static void setProcessesIndex() {
        int position = programs.size() - 1;
        ArrayList<String> listProcess = programs.get(position).getChProgram();
        for (int i = 0; i < listProcess.size(); i++) {
            int positionInMemory = SistemaOperativo.positionInMemory(i + 1);
            positionProcessesInMemory.add(positionInMemory);
        }
    }
    
    public static void setProgramsData() {
        int position = programs.size() - 1;
        Programa program = programs.get(position);
        String name = program.getName();
        int ins = program.getChProgram().size();
        int rb = SizeMemoryOccupied - program.getChProgram().size() - program.getListVariables().size();
        int rlc = SizeMemoryOccupied - program.getListVariables().size() - 1;
        int rlp = SizeMemoryOccupied - 1;
        names.add(String.valueOf(name));
        inss.add(String.valueOf(ins));
        rbs.add(String.valueOf(rb));
        rlcs.add(String.valueOf(rlc));
        rlps.add(String.valueOf(rlp));
    }
    
    public static int positionInMemory(int positionInData) {
        int position = programs.size() - 1;
        int sizeOfChProgram = programs.get(position).getChProgram().size();
        int sizeOfVariablesValuesList = programs.get(position).getListVariablesValues().size();
        int positionMemory = SizeMemoryOccupied - sizeOfChProgram - sizeOfVariablesValuesList + positionInData - 1;
        return positionMemory;
    }
    
    public static String checkAndChangePosition(int a){
        String numero = Integer.toString(a);
        switch (numero.length()) {
            case 1:
                numero = "000" + numero;
                break;
            case 2:
                numero = "00" + numero;
                break;
            case 3:
                numero = "0" + numero;
                break;
            default:
                throw new AssertionError();
        }   
        return numero;
    }
    
    public static void changeExecutionOrder(){
        if (typeOfPriority.equals("SJF/Nonexpropriative")) {
            for (int i = 0; i < programs.size(); i++) {
                Programa smallerProgram = programs.get(i);
                int positionOfSmallerProgram = i;
                for (int j = i; j < programs.size(); j++) {
                    int sizeOfSmallerProgram = smallerProgram.getChProgram().size();
                    int sizeOfProgram = programs.get(j).getChProgram().size();
                    if (sizeOfProgram < sizeOfSmallerProgram) {
                        smallerProgram = programs.get(j);
                        positionOfSmallerProgram = j;
                    }
                }
                if (i != positionOfSmallerProgram) {
                    Programa auxiliar = programs.get(i);
                    programs.set(i, smallerProgram);
                    programs.set(positionOfSmallerProgram, auxiliar);
                }
            }
        }
        if (typeOfPriority.equals("X priority as/Nonexpropriative")) {
            for (int i = 0; i < programs.size(); i++) {
                String priority = priorities.get(i);
                int positionOfHighestPriority = i;
                for (int j = i; j < programs.size(); j++) {
                    int levelOfHighestPriority = Integer.parseInt(priority);
                    int levelOfPriority = Integer.parseInt(priorities.get(j));
                    if (levelOfPriority < levelOfHighestPriority) {
                        priority = priorities.get(j);
                        positionOfHighestPriority = j;
                    }
                }
                if (i != positionOfHighestPriority) {
                    String auxiliar = priorities.get(i);
                    Programa auxiliarProgram = programs.get(i);
                    priorities.set(i, priority);
                    programs.set(i, programs.get(positionOfHighestPriority));
                    priorities.set(positionOfHighestPriority, auxiliar);
                    programs.set(positionOfHighestPriority, auxiliarProgram);
                }
            }
        }
    }
    
    public static void savePriority(String priority){
        priorities.add(priority);
    }
    
    public static String[] executeLine(String line){
        line = line.trim().replaceAll(" +", " ");
        String[] list = new String[ line.split(" ").length ];
        list = line.split(" ");
        return list;
    }
    
    public static boolean hasItEnd(int position){
        if (programs.get(position).getChProgram().size() <= executedLines[position]) {
            return true;
        }
        return false;
    }    
    
    public static int endOfIteration(int position){
        int end = SistemaOperativo.getExecutedLines()[position] + 5;
        if (end > programs.get(position).getChProgram().size()) {
            end = programs.get(position).getChProgram().size();
        }
        return end;
    }  
    
    public static void addValueToPrint(String valueToAdd){
        valuesToPrint.add(valueToAdd);
    }
    
    public static void rebootSistemaOperativoVariables(){
        kernelSize = 0;
        memorySize = 0;
        SizeMemoryOccupied = 0;
        programs = new ArrayList<>();
        memory = new String[0];
        tags = new ArrayList<>();
        positionTagsInMemory = new ArrayList<>();
        variables = new ArrayList<>();
        positionVariablesInMemory = new ArrayList<>();
        processes = new ArrayList<>();
        positionProcessesInMemory = new ArrayList<>();
        names = new ArrayList<>();
        inss = new ArrayList<>();
        rbs = new ArrayList<>();
        rlcs = new ArrayList<>();
        rlps = new ArrayList<>();
        priorities = new ArrayList<>();
        NumberOfIterationsPerProgram = 0;
        typeOfPriority = "";
        executedLines = new int[0];
        programIterator = 0;
        countLineIterator = 0;
        firstIterationStepByStep = false;
        valuesToPrint = new ArrayList<>();
    }  
}
