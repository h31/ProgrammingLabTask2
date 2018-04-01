import UI.Parsers.Args;
import logic.Operations.Collector;
import logic.Operations.Separator;

public class Main {
    public static void main(String[] args) throws Exception {
        Args arvs = new Args(args);
        arvs.getTask();
        if (arvs.taskFlag()) {
            Collector collector = new Collector();
            collector.collectFile(arvs.getFilesToCollect(), arvs.getCollectionFile().getName());
        } else {
            Separator separator = new Separator();
            separator.separateFile(arvs.getFileToSeparate());
        }
    }
}
