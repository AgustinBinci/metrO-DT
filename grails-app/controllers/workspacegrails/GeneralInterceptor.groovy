package workspacegrails


class GeneralInterceptor {

    GeneralInterceptor() {
	    matchAll().excludes(controller:'usuario', action:'login')
	    matchAll().excludes(controller:'usuario', action:'create')
    }

    boolean before() {
        if(!session.user) {
            redirect(controller: "usuario", action: "login");
            return true;
        } 
	return true;
     }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
