public class Main {
    public static void main(String[] args) {
        System.out.println("=== Evaluation Pipeline ===");
        Submission sub = new Submission("23BCS1007", "public class A{}", "A.java");

        Grader grader = new CodeGrader();
        Checker checker = new PlagiarismChecker();
        Writer writer = new ReportWriter();

        EvaluationPipeline pipeline = new EvaluationPipeline(grader, checker, writer);
        pipeline.evaluate(sub);
    }
}
