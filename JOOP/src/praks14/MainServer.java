package praks14;

import praks14.postpackage.PostPackage;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainServer {

    public static void main(String[] args) {

        Random random = new Random();

        List<PostPackage> packages = IntStream.rangeClosed(1, 1000)
                .mapToObj(i -> new PostPackage(i, 30 + random.nextInt(1471)))
                .collect(Collectors.toList());

        // parrallel streams
        int parrallelSum = packages.parallelStream().mapToInt(PostPackage::getWeight).sum();
        System.out.println("The total weight of all packages calculated with parrallel streams: " + parrallelSum + " g");
        ForkJoinPool fjp = new ForkJoinPool(7);
        int forkJoinSum = fjp.invoke(new PackageCounter(packages));
        System.out.println("The total weight of all packages calculated with fork/join: " + forkJoinSum + " g");
    }


    public static class PackageCounter extends RecursiveTask<Integer> {

        private List<PostPackage> packages;
        private final int MAX_LIST_SIZE = 2;

        public PackageCounter(List<PostPackage> packages) {
            this.packages = packages;
        }

        @Override
        protected Integer compute() {
            if (packages.size() <= MAX_LIST_SIZE) {
                int sum = packages.stream()
                        .mapToInt(PostPackage::getWeight)
                        .sum();
                return sum;
            } else {
                PackageCounter p1 = new PackageCounter(packages.subList(0, packages.size() / 2));
                p1.fork();
                PackageCounter p2 = new PackageCounter(packages.subList(packages.size() / 2, packages.size()));
                //l2.fork(); // no need to fork second half
                return  p2.compute() + p1.join();
            }
        }
    }
}
