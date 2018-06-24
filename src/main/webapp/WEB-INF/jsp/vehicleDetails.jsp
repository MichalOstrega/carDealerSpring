<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>${vehicle.manufacturer} ${vehicle.model} </title>
</head>
<body>

<h1>Vehicle details</h1>
<table>
    <tr><td><b>Property</b></td><td><b>Value</b></td></tr>
    <tr><td>Id</td><td>${vehicle.id}</td></tr>
    <tr><td>Manufacturer</td><td>${vehicle.manufacturer}</td></tr>
    <tr><td>Model</td><td>${vehicle.model}</td></tr>
    <tr><td>Fuel</td><td>${vehicle.fuel}</td></tr>
    <tr><td>ProductionYear</td><td>${vehicle.productionYear}</td></tr>
    <tr><td>Mileage</td><td>${vehicle.mileage}</td></tr>
    <tr><td>Price</td><td>${vehicle.price}</td></tr>
    <tr><td>Sold</td><td>${vehicle.sold}</td></tr>
</table>







<%--<table>

    <tr><th><b>Id</b></th>
        <th><b>Manufacturer</b></th>
        <th><b>Model</b></th>
        <th><b>Fuel</b></th>
        <th><b>ProductionYear</b></th>
        <th><b>Mileage</b></th>
        <th><b>Price</b></th>
        <th><b>Sold</b></th></tr>
    <tr>
    <td>${vehicle.id}</td>
    <td>${vehicle.manufacturer}</td>
    <td>${vehicle.model}</td>
    <td>${vehicle.fuel}</td>
    <td>${vehicle.productionYear}</td>
    <td>${vehicle.mileage}</td>
    <td>${vehicle.price}</td>
    <td>${vehicle.sold}</td></tr>


</table>--%>



</body>
</html>