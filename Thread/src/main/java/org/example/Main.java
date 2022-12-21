package org.example;




import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static int[] _Array = new int[10];
    static final int N = 10;
    static volatile int _total;

    static int _step = _Array.length/N;
    static int _countLines;

    static FileReader fr;

    static {
        try {
            fr = new FileReader("\\Users\\dimac\\Desktop\\fkJava\\Thread\\src\\main\\text.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scanner scanner = new Scanner(fr);

    public static void main(String[] args) throws IOException, InterruptedException {

        // Creating Array
        int _val = 0;
        for (int i = 0; i < _Array.length; i++) {
            _Array[i] = _val;
            _val++;
        }

        // Creating Threads
        for (int i = 0; i < N; i++) {
            int _currentStep = (i + 1) * _step;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.print("index start\t\tindex final\t\tresult\n\t");
                        System.out.print(_currentStep - _step);
                        System.out.print("\t\t\t\t");
                        System.out.print(_currentStep);
                        System.out.print("\t\t\t\t");
                        for (int i = _currentStep - _step; i < _currentStep; i++) {
                            _total += _Array[i];
                        }
                        System.out.print(_total);
                        System.out.print('\n');
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t.run();
        }


        // Creating Matrix
        int[][] _mA = {{4, 2},
                {4, 1}};

        int[][] _mB = {{5, 7, 4},
                {3, 6, 2}};

        // Calculating Matrix
        int _resultMatrix[][] = multiplyMatrixMT(_mA,_mB,6);
        for(int i = 0; i < _resultMatrix.length ;i++) {
            for (int j = 0; j < _resultMatrix[0].length; j++)
            {
                System.out.print(_resultMatrix[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }

       // Reading File
        String _path = "\\Users\\dimac\\Desktop\\fkJava\\Thread\\src\\main\\text.txt";
        _countLines = (int)getLineCountByReader("\\Users\\dimac\\Desktop\\fkJava\\Thread\\src\\main\\text.txt");

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("THE END");
        scanner.close();


    }
    public static long getLineCountByReader(String fileName) throws IOException {
        try (var lnr = new LineNumberReader(new BufferedReader(new FileReader(fileName)))) {
            while (lnr.readLine() != null) ;
            return lnr.getLineNumber();
        }
    }


    synchronized public static void _Read(Scanner scan, String name){
        System.out.println(scan.nextLine()+" "+name);
    }



    private static int[][] multiplyMatrixMT(final int[][] firstMatrix, final int[][] secondMatrix, int threadCount) {
        assert threadCount > 0;

        final int rowCount = firstMatrix.length;
        final int colCount = secondMatrix[0].length;
        final int[][] result = new int[rowCount][colCount];

        final int cellsForThread = (rowCount * colCount) / threadCount;
        int firstIndex = 0;
        final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount];


        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int lastIndex = firstIndex + cellsForThread;
            if (threadIndex == 0) {
                lastIndex = rowCount * colCount;
            }
            multiplierThreads[threadIndex] = new MultiplierThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }

        try {
            for (final MultiplierThread multiplierThread : multiplierThreads)
                multiplierThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}

class MultiplierThread extends Thread
{

    private final int[][] _firstMatrix;
    private final int[][] _secondMatrix;
    private final int[][] _resultMatrix;
    private final int _firstIndex;
    private final int _lastIndex;
    private final int _sumLength;


    public MultiplierThread(final int[][] _firstMatrix,
                            final int[][] _secondMatrix,
                            final int[][] _resultMatrix,
                            final int _firstIndex,
                            final int _lastIndex)
    {
        this._firstMatrix  = _firstMatrix;
        this._secondMatrix = _secondMatrix;
        this._resultMatrix = _resultMatrix;
        this._firstIndex   = _firstIndex;
        this._lastIndex    = _lastIndex;
        _sumLength = _secondMatrix.length;
    }

    private void calcValue(final int row, final int col)
    {
        int sum = 0;
        for (int i = 0; i < _sumLength; ++i)
            sum += _firstMatrix[row][i] * _secondMatrix[i][col];
        _resultMatrix[row][col] = sum;
    }


    @Override
    public void run()
    {
        System.out.println("Thread " + getName() + " started. Calculating cells from " + _firstIndex + " to " + _lastIndex + "...");

        final int colCount = _secondMatrix[0].length;  // Число столбцов результирующей матрицы.
        for (int index = _firstIndex; index < _lastIndex; ++index)
            calcValue(index / colCount, index % colCount);

        System.out.println("Thread " + getName() + " finished.");
    }
}


class MyThread extends Thread{

    public void run()
    {
        while(true){
            try {
               Main._Read(Main.scanner,Thread.currentThread().getName().toString());
                Thread.sleep(1000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
            } catch (NoSuchElementException e){
               break;
            }
        }
    }
}




