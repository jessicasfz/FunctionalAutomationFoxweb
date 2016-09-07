CD /D %~dp0
java -jar selenium-server-standalone-2.45.0.jar -role webdriver -hub http://localhost:4444/grid/register -Dwebdriver.chrome.driver=chromedriver.exe  -nodeConfig config.json