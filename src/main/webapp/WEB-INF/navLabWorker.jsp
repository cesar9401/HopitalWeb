<nav class="navbar navbar-expand-lg navbar-light sticky-top">
    <div class="container">
        <a class="navbar-brand" href="MainController?action=labProfile">Lab - ${profile.name}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link1<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link2</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link3</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Cuenta
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="ReportOperation?action=lab">Reportes</a>
                        <a class="dropdown-item" href="MainController?action=singOff">Cerrar Sesion</a>
                    </div>
                </li>
            </ul>
        </div>           
    </div>
</nav>