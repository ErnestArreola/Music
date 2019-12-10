
//package chord;

import java.io.Serializable;

/**
 *
 * @author applesauce
 */
public class Transaction implements Serializable {


  public enum Operation {WRITE, DELETE}

  public enum Vote {YES, NO}

  Long TransactionId;

  Vote vote;
  
  String fileName;
  
  long pageIndex;
  
  long guid;

  Operation operation;

  RemoteInputFileStream fileStream;


  public Transaction(long g, RemoteInputFileStream fileStream, Long Id, Operation operation) {

    this.guid = g;

    this.fileStream = fileStream;

    this.TransactionId = Id;

    this.operation = operation;
  }

  public void voteYes() {
    vote = Vote.YES;
  }


  public void voteNo() {
    vote = Vote.NO;

  }


  public Vote getVote() {
    return vote;

  }

  public Operation getOperation() {

    return operation;

  }


  public long getFileId() {
    return guid;
  }

  public Long getTransactionId() {
     return TransactionId;
  }

  public RemoteInputFileStream getStream() {
    return fileStream;

  }

}