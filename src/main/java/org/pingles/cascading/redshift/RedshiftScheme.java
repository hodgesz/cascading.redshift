package org.pingles.cascading.redshift;

import cascading.flow.FlowProcess;
import cascading.scheme.Scheme;
import cascading.scheme.SinkCall;
import cascading.scheme.SourceCall;
import cascading.tap.Tap;
import cascading.tuple.Fields;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.RecordReader;
import java.io.IOException;

// Scheme<Config, Input, Output, SourceContext, SinkContext
public class RedshiftScheme extends Scheme<JobConf, RecordReader, OutputCollector, Object[], Object[]> {
    private final String tableName;
    private final String[] columnNames;
    private final String[] columnDefinitions;
    private final String distributionKey;
    private final String[] sortKeys;

    public RedshiftScheme(Fields sourceFields, Fields sinkFields, String tableName, String[] columnNames, String[] columnDefinitions, String distributionKey, String[] sortKeys) {
        super(sourceFields, sinkFields);
        this.tableName = tableName;
        this.columnNames = columnNames;
        this.columnDefinitions = columnDefinitions;
        this.distributionKey = distributionKey;
        this.sortKeys = sortKeys;
    }

    @Override
    public void sourceConfInit(FlowProcess<JobConf> jobConfFlowProcess, Tap<JobConf, RecordReader, OutputCollector> jobConfRecordReaderOutputCollectorTap, JobConf entries) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sinkConfInit(FlowProcess<JobConf> jobConfFlowProcess, Tap<JobConf, RecordReader, OutputCollector> jobConfRecordReaderOutputCollectorTap, JobConf entries) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean source(FlowProcess<JobConf> jobConfFlowProcess, SourceCall<Object[], RecordReader> recordReaderSourceCall) throws IOException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sink(FlowProcess<JobConf> jobConfFlowProcess, SinkCall<Object[], OutputCollector> outputCollectorSinkCall) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public RedshiftJdbcCommand buildCreateTableCommand() {
        return new CreateTableCommand(tableName, columnNames, columnDefinitions, distributionKey, sortKeys);
    }

    public RedshiftJdbcCommand buildDropTableCommand() {
        return new DropTableCommand(tableName);
    }

    public RedshiftJdbcCommand buildCopyFromS3Command(String uri, String s3AccessKey, String s3SecretKey) {
        return new CopyFromS3Command(tableName, uri, s3AccessKey, s3SecretKey);
    }
}
