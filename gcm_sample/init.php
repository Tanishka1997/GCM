<?php


$mysql_user = "";
$mysql_pass = "";
$server_name = "";
$db_name="";
$con = mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);

if(!$con)
{
	 die("Connection Error");
}
else
{
//	echo "<h3>Database Connection Sucessful!!</h3>";
}
?>
