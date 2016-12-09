Set Shell = CreateObject( "WScript.Shell" )

Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\DbName", "volta_penang"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\DbParm","DATABASE='volta_penang'"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\DbName", "volta_penang"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\DbParm","DATABASE='volta_penang'"

Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta\Database\ServerName","GBPB-WHPA-UT92"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta Purchases\Database\ServerName","GBPB-WHPA-UT92"
Shell.RegWrite "HKCU\SOFTWARE\Travelex\Volta S&C\Database\ServerName","GBPB-WHPA-UT92"

Set Shell = Nothing
