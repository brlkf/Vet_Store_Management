<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Information</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.x/dist/tailwind.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
            body {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                min-height: 100vh;
            }
            .card {
                background: rgba(255, 255, 255, 0.8);
                backdrop-filter: blur(10px);
            }
            .chart-container {
                position: relative;
                margin: auto;
                height: 40vh;
                width: 80vw;
            }
        </style>
    </head>
    <body class="flex items-center justify-center p-10">
        <div class="card rounded-lg p-6 shadow-lg">
            <h1 class="text-3xl font-bold text-gray-800 mb-6 text-center">Report Summary</h1>
            <div class="chart-container">
                <canvas id="reportSummaryChart"></canvas>
            </div>
            <div class="text-lg mt-6 text-gray-800">
                <p class="my-2">Total Active Appointments: <strong>${appointmentCount}</strong></p>
                <p class="my-2">Total Active Reports: <strong>${reportCount}</strong></p>
                <p class="my-2">Total Active Customers: <strong>${customerCount}</strong></p>
                <p class="my-2">Total Scheduled Appointments: <strong>${scheduledAppCount}</strong></p>
                <p class="my-2">Total Completed Appointments: <strong>${completedAppCount}</strong></p>
            </div>
            <div class="text-center mt-6">
                <a href="index.jsp" class="bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded-full transition ease-in-out duration-150">Go Back</a>
                <button onclick="downloadChart()" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded-full transition ease-in-out duration-150">Download Chart</button>
            </div>
        </div>

        <script>
            var reportSummaryChart;

            document.addEventListener('DOMContentLoaded', function () {
                var ctx = document.getElementById('reportSummaryChart').getContext('2d');
                reportSummaryChart = new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: ['Active Appointments', 'Active Reports', 'Active Customers'],
                        datasets: [{
                                data: [${appointmentCount}, ${reportCount}, ${customerCount}],
                                backgroundColor: [
                                    'rgba(102, 126, 234, 0.6)',
                                    'rgba(118, 75, 162, 0.6)',
                                    'rgba(255, 198, 165, 0.6)'
                                ],
                                borderColor: [
                                    'rgba(102, 126, 234, 1)',
                                    'rgba(118, 75, 162, 1)',
                                    'rgba(255, 198, 165, 1)'
                                ],
                                hoverOffset: 4
                            }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                labels: {
                                    color: '#4a5568',
                                    font: {
                                        size: 14
                                    }
                                },
                                position: 'bottom'
                            }
                        },
                        animation: {
                            animateScale: true,
                            animateRotate: true
                        }
                    }
                });
            });

            function downloadChart() {
                var url_base64jp = reportSummaryChart.toBase64Image();
                var a = document.createElement('a');
                a.href = url_base64jp;
                a.download = 'report-summary-chart.png';
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
            }
        </script>
    </body>
</html>
