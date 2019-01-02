package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 平行调度任务（类似于无环加权有向图）
 * @author Alisa.Wang zhphust09@hust.edu.cn at 2018/12/11
 * @version 8.0
 */
public class ParallelTask {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = ".\\src\\source\\jobsPC.txt";
        Scanner scanner = new Scanner(new File(filename));
        int task = Integer.parseInt(scanner.nextLine());
        EdgeDigraph G = new EdgeDigraph(2 * task + 2);
        int start = 2 * task;
        int terminal = 2 * task + 1;
        while (scanner.hasNextLine()) {
            String[] ss = scanner.nextLine().split("\\s+");
            int taskNo = Integer.parseInt(ss[0]);
            double duration = Double.parseDouble(ss[1]);
            G.addEdge(new Edge(taskNo, taskNo + task, duration));
            G.addEdge(new Edge(start, taskNo, 0.0));
            G.addEdge(new Edge(taskNo + task, terminal, 0.0));

            for (int i = 2; i < ss.length; i++) {
                int nextTask = Integer.parseInt(ss[i]);
                G.addEdge(new Edge(taskNo + task, nextTask, 0.0));
            }
        }

        AcyclicLongestPath lp = new AcyclicLongestPath(G, start);
        System.out.println("Start time: ");
        for (int i = 0; i < task; i++) {
            System.out.printf("%4d: %5.1f\n", i, lp.disTo(i));
        }
        System.out.printf("Finish time: %5.1f\n", lp.disTo(terminal));
    }
}
