package pm.little.api.controllers.implementation;

import jakarta.annotation.Generated;
import pm.little.api.controllers.ProjectInstancesApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-27T23:47:32.256351+01:00[Europe/Prague]", comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.projectDay.base-path:}")
public class ProjectInstancesApiController implements ProjectInstancesApi {

    private final NativeWebRequest request;

    @Autowired
    public ProjectInstancesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
