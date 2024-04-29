package com.github.beatrizgomees.api.rheumaPlanner.core.docs;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
    @OpenAPIDefinition(

            tags = {
                    @Tag(name = "Patient Planner", description = "This API is about planner for the patients")
            },
            info = @Info(
                    title="Patient Planner API",
                    version = "2.0.1",
                    contact = @Contact(
                            name = "Beatriz",
                            url = "https://www.linkedin.com/in/lilian-beatriz-b6b899228/",
                            email = "beatrizgomesxx@gmail.com"),
                    license = @License(
                            name = "Apache 2.0",
                            url = "http://www.apache.org/licenses/LICENSE-2.0.html"))

    )
    public class SwaggerDoc {
}
