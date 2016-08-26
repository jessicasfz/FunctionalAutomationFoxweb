SET HUBHOST=localhost
IF NOT [%1] == [] SET HUBHOST=%1
start java -jar selenium-server-standalone-2.45.0.jar -role hub http://%HUBHOST%:4444/grid/register
Exit