<?php


$mysql_user = ""//yr info;
$mysql_pass = "" yr info;
$server_name = ""//yr info;
$db_name=""//yr info;
$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);

if(!$con)
{
	//echo "Connection Error.....".mysqli_connect_error();
}
else
{
//	echo "<h3>Database Connection Sucessful!!</h3>";
}
?>