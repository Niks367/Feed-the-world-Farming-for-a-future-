package business.interfaces;

public interface River {
   boolean isDanger = false;

   int getRiverStatus();
   void setRiverStatus(int status);
   void visitRiver();

}