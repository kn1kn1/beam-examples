package org.example;


import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.fs.MatchResult;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.joda.time.Instant;

import java.io.IOException;

public class ReadableFileExampleNG {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);

        p
                .apply(Create.of("gs://apache-beam-samples/shakespeare/kinglear.txt"))
                .apply(FileIO.matchAll())
                .apply(FileIO.readMatches())
                .apply(ParDo.of(
                        new DoFn<FileIO.ReadableFile, String>() {
                            @ProcessElement
                            public void processElement(ProcessContext context, @Element FileIO.ReadableFile readableFile, OutputReceiver<String> r) throws IOException {
                                MatchResult.Metadata metadata = readableFile.getMetadata();
                                String name = metadata.resourceId().getFilename();
                                long lastModifiedMillis = metadata.lastModifiedMillis();
                                long sizeBytes = metadata.sizeBytes();
                                System.out.println("*** name: " + name);
                                System.out.println("*** lastModifiedMillis: " + lastModifiedMillis);
                                System.out.println("*** lastModified: " + Instant.ofEpochMilli(lastModifiedMillis));
                                System.out.println("*** sizeBytes: " + sizeBytes);
                                String content = readableFile.readFullyAsUTF8String();
                                r.output(content);
                            }
                        }
                ))
                .apply(TextIO.write().to("readable-file-example"));

        p.run().waitUntilFinish();
    }
}
