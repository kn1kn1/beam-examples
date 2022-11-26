# Example multi-language pipelines - Sklearn Mnist Classification

Example Java maven project described at the Beam example document ( https://github.com/apache/beam/tree/master/examples/multi-language#sklearn-mnist-classification ).

Created from the following command and remove unused source code files.

```
export BEAM_VERSION=2.43.0

mvn archetype:generate \
    -DarchetypeGroupId=org.apache.beam \
    -DarchetypeArtifactId=beam-sdks-java-maven-archetypes-examples \
    -DarchetypeVersion=$BEAM_VERSION \
    -DgroupId=org.example \
    -DartifactId=multi-language-beam \
    -Dversion="0.1" \
    -Dpackage=org.apache.beam.examples \
    -DinteractiveMode=false
```

Run the pipeline on Dataflow with the following command.

```
export GCP_PROJECT=<GCP project>
export GCP_BUCKET=<GCP bucket>
export GCP_REGION=<GCP region>

mvn compile exec:java -Dexec.mainClass=org.apache.beam.examples.multilanguage.SklearnMnistClassification \
    -Dexec.args="--runner=DataflowRunner --project=$GCP_PROJECT \
                 --region=$GCP_REGION \
                 --gcpTempLocation=gs://$GCP_BUCKET/multi-language-beam/tmp \
                 --output=gs://$GCP_BUCKET/multi-language-beam/output" \
    -Pdataflow-runner
```