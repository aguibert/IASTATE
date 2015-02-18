package cpre419.lab04;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Lab4 extends Configured implements Tool {

    static final String temp0 = "/scr/aguibert/temp0";
    static final String temp1 = "/scr/aguibert/temp1";
    static String input; // = /class/s15419x/lab4/gensort-out-5K
    static String output; // = "/scr/aguibert/lab4/output";

    public static void main(String[] args) throws Exception
    {
        if (args.length != 2) {
            System.out.println("Requires 2 arguments (input and output) but only " + args.length + " were specified.");
            System.exit(-1);
        }

        input = args[0];
        output = args[1];

        int res = ToolRunner.run(new Configuration(), new Lab4(), args);
        System.exit(res);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int run(String[] args) throws Exception {

        Configuration conf = new Configuration();
        FileSystem.get(conf).delete(new Path(output), true);

        try {
            // Job 1
            Job job_one = new Job(conf, "Driver Program Round 1");
            job_one.setJarByClass(Lab4.class);
            job_one.setNumReduceTasks(3);
            job_one.setOutputKeyClass(Text.class);
            job_one.setOutputValueClass(Text.class);
            job_one.setMapperClass(Map_One.class);
            job_one.setReducerClass(Reduce_One.class);
            job_one.setInputFormatClass(TextInputFormat.class);
            job_one.setOutputFormatClass(TextOutputFormat.class);
            job_one.setPartitionerClass(Lab4Partitioner.class);

            FileInputFormat.addInputPath(job_one, new Path(input));
            FileOutputFormat.setOutputPath(job_one, new Path(output));

            job_one.waitForCompletion(true);
        } finally {
            FileSystem.get(conf).delete(new Path(temp0), true);
            FileSystem.get(conf).delete(new Path(temp1), true);
        }

        return 0;
    }

    public static class Map_One extends Mapper<LongWritable, Text, Text, Text> {

        @Override
        public void map(LongWritable key, Text value, Context context)
                        throws IOException, InterruptedException {
            context.write(new Text(value.toString().substring(0, 10)), new Text(value.toString().substring(12)));
        }
    }

    public static class Reduce_One extends Reducer<Text, Text, Text, Text> {

        @Override
        public void reduce(Text key, Iterable<Text> values, Context context)
                        throws IOException, InterruptedException {
            for (Text val : values)
                context.write(key, val);
        }
    } // -du -h /

    public static class Lab4Partitioner extends Partitioner<Text, Text> {

        @Override
        public int getPartition(Text key, Text value, int numPartitions) {
            char c = key.toString().charAt(0);
            int part = ((c * numPartitions) / 0x7F);
            if (part >= numPartitions || part < 0)
                throw new RuntimeException("**ERROR** c=" + c + "  part=" + part + "  num=" + numPartitions);
            return part;
        }
    }
}
