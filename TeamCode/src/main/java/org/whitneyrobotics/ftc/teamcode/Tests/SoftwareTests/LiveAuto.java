//Made by Mohnish Sivakumar (FTC Programming Head, Co-Secretary)
package org.whitneyrobotics.ftc.teamcode.Tests.SoftwareTests;

import static org.whitneyrobotics.ftc.teamcode.Constants.FieldConstants.StartingTiles.BLUE_A4;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.path.PathContinuityViolationException;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.whitneyrobotics.ftc.teamcode.Extensions.OpModeEx.OpModeEx;
import org.whitneyrobotics.ftc.teamcode.Extensions.TelemetryPro.TelemetryPro;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.drive.CenterstageMecanumDrive;
import org.whitneyrobotics.ftc.teamcode.Roadrunner.trajectorysequence.TrajectorySequence;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

@TeleOp(name="AutoLive", group="Tests")
public class LiveAuto extends OpMode {
    private NanoHTTPD nanoServer;
    private String[][] points = new String[3][3];

    private CenterstageMecanumDrive drive;
    private boolean runButtonPressed = false;
    private TrajectorySequence please;

    @Override
    public void init() {
        drive = new CenterstageMecanumDrive(hardwareMap);
        telemetry.addData("Name:","Mohnish");

        try {
            nanoServer = new NanoHTTPD("192.168.43.1", 8043) {
                @Override
                public Response serve(IHTTPSession session) {
                    Map<String, String> parms = session.getParms();
                    String msg = "<html><head><title>LiveAuto</title>";
                    msg += "<style>body {margin:0;padding:0;font-family:'Trebuchet MS',sans-serif;}</style>";
                    msg += "<style>.point-box {background-color:#fff;border-radius:30px;padding:20px;border:1px solid #ddd;box-shadow:0 0 10px rgba(0,0,0,0.2);width:400px;font-size:10px;}</style>";
                    msg += "<style>.input-field {width:50px;height:30px;padding:10px;border:1px solid #ccc;border-radius:5px;}</style>";
                    msg += "<style>.input-row {display:flex;flex-wrap:wrap;justify-content:space-between;align-items:center;}</style>";
                    msg += "<style>#push-button {background-color:#4CAF50;color:#fff;padding:10px 20px;font-size:15px;border:none;border-radius:5px;cursor:pointer;}</style>";
                    msg += "<style>#scratch-container {width:482px;height:412px;border:1px solid #ccc;position:absolute;left:20px;top:13%;}</style>";
                    msg += "<style>#point-form {position:absolute;left:800px;top:8%;width:400px;display:flex;flex-direction:column;justify-content:space-between;height:70vh;}</style>";
                    msg += "</head><body>";
                    msg += "<div id=\"scratch-container\"><iframe src=\"https://scratch.mit.edu/projects/1039799985/embed\" width=\"578\" height=\"494\" allowtransparency=\"true\" frameborder=\"0\" scrolling=\"no\" allowfullscreen></iframe></div>";
                    msg += "<form id=\"point-form\">";
                    for (int i = 0; i < 3; i++) {
                        msg += "<div class=\"point-box\"><h1>Point " + (i + 1) + "</h1><div class=\"input-row\"><h2>X position:</h2><input type=\"number\" class=\"input-field\" id=\"x-pos-" + (i + 1) + "\" name=\"x-pos-" + (i + 1) + "\" value=\"" + (parms.get("x-pos-" + (i + 1))!= null? parms.get("x-pos-" + (i + 1)) : "") + "\">";
                        msg += "<h2>Y position:</h2><input type=\"number\" class=\"input-field\" id=\"y-pos-" + (i + 1) + "\" name=\"y-pos-" + (i + 1) + "\" value=\"" + (parms.get("y-pos-" + (i + 1))!= null? parms.get("y-pos-" + (i + 1)) : "") + "\">";
                        msg += "<h2>Heading:</h2><input type=\"number\" class=\"input-field\" id=\"heading-" + (i + 1) + "\" name=\"heading-" + (i + 1) + "\" value=\"" + (parms.get("heading-" + (i + 1))!= null? parms.get("heading-" + (i + 1)) : "") + "\"></div></div>";
                    }
                    msg += "<input type=\"hidden\" name=\"push-button\" value=\"true\">";
                    msg += "<button id=\"push-button\">Run</button></form>";
                    msg += "<script>";
                    msg += "const form = document.getElementById('point-form');";
                    msg += "const inputs = form.querySelectorAll('input');";
                    msg += "form.addEventListener('submit', (event) => {";
                    msg += "  event.preventDefault();";
                    msg += "  const formData = new FormData(form);";
                    msg += "  fetch('', {";
                    msg += "    method: 'POST',";
                    msg += "    body: formData";
                    msg += "  }).then(response => response.text())";
                    msg += ".then((data) => {";
                    msg += "    console.log(data);";// log the responsefrom the server
                    msg += "  });";
                    msg += "  // keep the input values";
                    msg += "  for (const input of inputs) {";
                    msg += "    input.value = input.value;";
                    msg += "  }";
                    msg += "});";
                    msg += "</script>";

                    for (int i = 0; i <= 3; i++) {
                        if (parms.get("x-pos-" + (i + 1))!= null) {
                            points[i][0] = parms.get("x-pos-" + (i + 1));
                        }
                        if (parms.get("y-pos-" + (i + 1))!= null) {
                            points[i][1] = parms.get("y-pos-" + (i + 1));
                        }
                        if (parms.get("heading-" + (i + 1))!= null) {
                            points[i][2] = parms.get("heading-" + (i + 1));
                        }
                    }
                    if (parms.containsKey("push-button") && parms.get("push-button").equals("true")) {
                        runButtonPressed = true;
                        // you can also perform other actions here, such as executing a trajectory
                    }


                    return newFixedLengthResponse(msg + "</body></html>\n");
                }
            };

            nanoServer.start();
        } catch (IOException ioe) {
        }


    }

    @Override
    public void loop() {
        // Display the points only if they are fully available
        for (int i = 0; i < 3; i++) {
            if (points[i][0] != null && points[i][1] != null && points[i][2] != null) {
                telemetry.addData("Point " + (i + 1) + ": ", "X: " + points[i][0] + ", Y: " + points[i][1] + ", Heading: " + points[i][2]);
            }
        }

        // Update telemetry immediately to reflect latest points
        telemetry.addData("Points (all): ", Arrays.deepToString(points));
        telemetry.update();

        // Only rebuild and run the trajectory if button was pressed
        if (runButtonPressed) {
            // Rebuild the trajectory before running to reflect the latest changes
            if (points[0][0] != null && points[0][1] != null && points[0][2] != null &&
                    points[1][0] != null && points[1][1] != null && points[1][2] != null &&
                    points[2][0] != null && points[2][1] != null && points[2][2] != null) {

                try {
                    please = drive.trajectorySequenceBuilder(
                                    new Pose2d(
                                            Double.parseDouble(points[0][0]),
                                            Double.parseDouble(points[0][1]),
                                            Math.toRadians(Double.parseDouble(points[0][2]))
                                    )
                            )
                            .lineToLinearHeading(new Pose2d(
                                    Double.parseDouble(points[1][0]),
                                    Double.parseDouble(points[1][1]),
                                    Math.toRadians(Double.parseDouble(points[1][2]))
                            ))
                            .lineToLinearHeading(new Pose2d(
                                    Double.parseDouble(points[2][0]),
                                    Double.parseDouble(points[2][1]),
                                    Math.toRadians(Double.parseDouble(points[2][2]))
                            ))
                            .build();
                } catch (PathContinuityViolationException e) {
                    telemetry.addData("Error:", "Your points don't connect properly. They are too close and confusing...");
                    runButtonPressed = false; // Stop running if there's an error
                }
            }

            // Check if trajectory is valid and then run it
            if (please != null) {
                drive.setPoseEstimate(new Pose2d(
                        Double.parseDouble(points[0][0]),
                        Double.parseDouble(points[0][1]),
                        Math.toRadians(Double.parseDouble(points[0][2]))
                ));

                drive.followTrajectorySequenceAsync(please);

                // Keep updating the trajectory
                while (drive.isBusy()) {
                    drive.update();
                    telemetry.update();

                }

                // Reset variables after trajectory is run
                runButtonPressed = false; // Allow the button to be pressed again
                please = null; // Clear the trajectory to rebuild next time
            }
        }

        // Update telemetry after each loop
        telemetry.update();
    }


    @Override
    public void stop() {
        super.stop();
        please = null;

        nanoServer.stop();
    }
}
