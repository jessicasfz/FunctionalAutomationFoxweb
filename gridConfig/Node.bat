SET HUBHOST=localhost
SET NODEHOST=4444
IF NOT [%1] == [] SET HUBHOST=%1
IF NOT [%1] == [] SET NODEHOST=%1
start java -jar selenium-server-standalone-2.45.0.jar -role webdriver -hub http://%HUBHOST%:4444/grid/register -Dwebdriver.chrome.driver=chromedriver.exe -Dwebdriver.ie.driver=IEDriverServer.exe
Exit