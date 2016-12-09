Set Shell = CreateObject( "WScript.Shell" )

Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\DbName", "volta_kk"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\DbParm","DATABASE='volta_kk'"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\DbName", "volta_kk"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\DbParm","DATABASE='volta_kk'"

Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\ServerName","GBPB-WHPA-IT92"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\ServerName","GBPB-WHPA-IT92"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta S&C\Database\ServerName","GBPB-WHPA-IT92"

Set Shell = Nothing
