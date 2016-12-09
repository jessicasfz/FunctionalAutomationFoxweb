Set Shell = CreateObject( "WScript.Shell" )

Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\DbName", "volta_Johur"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\DbParm","DATABASE='volta_Johur'"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\DbName", "volta_Johur"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\DbParm","DATABASE='volta_Johur'"

Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\ServerName","GBPB-WHPA-DE99"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\ServerName","GBPB-WHPA-DE99"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta S&C\Database\ServerName","GBPB-WHPA-DE99"

Set Shell = Nothing
