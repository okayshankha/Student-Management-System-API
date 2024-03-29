<%-- 
    Document   : defaultAdminView
    Created on : 25 Sep, 2019, 12:44:57 PM
    Author     : Shankha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% String project_name = "Student_Management_System";%>










<div class="page">
    <!-- Page Content -->
    <div class="page-content container-fluid">
        <!-- Example Card Base -->
        <div class="example-wrap">
            <h3 class="example-title">Base</h3>
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <!-- Example Standard Card -->
                    <h4 class="example-title">Standard</h4>
                    <p>Build it with the class <code>.card</code>, and free to put the <code>.card-block</code>                class on the <code>.card</code> element to consolidate your markup.</p>
                    <div class="card">
                        <img class="card-img-top img-fluid w-full" src="/Student_Management_System/assets/global/photos/placeholder.png"
                             alt="Card image cap">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                            <a href="#" class="btn btn-primary">Button</a>
                        </div>
                    </div>
                    <!-- End Example Standard Card -->
                </div>
                <div class="col-lg-4 col-md-6">
                    <!-- Example Card Image overlays -->
                    <h4 class="example-title">Image overlays</h4>
                    <p>Turn an image into a card background and overlay your card’s text.
                        Depending on the image, you may or may not need <code>.card-inverse</code>                (see below).</p>
                    <div class="card card-inverse">
                        <img class="card-img w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image">
                        <div class="card-img-overlay">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">This is a wider card with supporting text below as a natural
                                lead-in to additional content.</p>
                        </div>
                    </div>
                    <!-- End Example Card Image overlays -->
                </div>
                <div class="col-lg-4 col-md-6">
                    <!-- Example Card Inverted text -->
                    <h4 class="example-title">Inverted text</h4>
                    <p>Add <code>.card-inverse</code> for white text and specify the background-color
                        and border-color to go with it.</p>

                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Lorem ipsum dolor sit amet</h4>
                            <p class="card-text">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <p class="card-text hidden-xl-down">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum, molestias!
                                Inventore dolore, perferendis corporis?Lorem ipsum dolor sit
                                amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                                ut labore et dolore magna aliqua. </p>
                            <p class="card-text hidden-md-down"> Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
                                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                                in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                                nulla pariatur.</p>
                            <a href="#" class="btn btn-primary">Button</a>
                        </div>
                    </div>
                    <!-- End Example Card Inverted text -->
                </div>
            </div>
        </div>
        <!-- End Example Card Base -->

        <!-- Example Card Content -->
        <div class="example-wrap">
            <h3 class="example-title">Content & Image Caps</h3>
            <p>Cards support a wide variety of content, including images, text, list
                groups, links, and more.</p>
            <div class="row">
                <div class="col-lg-4 col-md-12">
                    <h4 class="example-title">Types</h4>
                    <div class="card">
                        <img class="card-img-top img-fluid w-full" src="/Student_Management_System/assets/global/photos/placeholder.png"
                             alt="Card image cap">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Some quick example text to build on the card title and make up
                                the bulk of the card's content.</p>
                        </div>
                        <ul class="list-group list-group-dividered px-20 mb-0">
                            <li class="list-group-item px-0">Cras justo odio</li>
                            <li class="list-group-item px-0">Dapibus ac facilisis in</li>
                            <li class="list-group-item px-0">Vestibulum at eros</li>
                        </ul>
                        <div class="card-block">
                            <a href="#" class="card-link">Card link</a>
                            <a href="#" class="card-link">Another link</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <h4 class="example-title">Image caps top</h4>
                    <div class="card">
                        <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">This is a wider card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.</p>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quis
                                ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            <p class="card-text hidden-md-down">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <p class="card-text hidden-lg-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <p class="card-text">
                                <small class="text-muted">Last updated 3 mins ago</small>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <h4 class="example-title">Image caps bottom</h4>
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">This is a wider card with supporting text below as a natural
                                lead-in to additional content. This content is a little bit
                                longer.</p>
                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quis
                                ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            <p class="card-text hidden-md-down">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <p class="card-text hidden-lg-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                cillum dolore eu fugiat nulla pariatur.</p>
                            <p class="card-text">
                                <small class="text-muted">Last updated 3 mins ago</small>
                            </p>
                        </div>
                        <img class="card-img-bottom w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                    </div>
                </div>
            </div>
        </div>
        <!-- End Example Card Content -->

        <!-- Example Card Content -->
        <div class="example-wrap">
            <h3 class="example-title">Extra</h3>
            <div class="row">
                <div class="col-md-6">
                    <h4 class="example-title">Header and footer</h4>
                    <p>Add an optional header and/or footer within a card.</p>
                    <div class="card">
                        <div class="card-header card-header-transparent card-header-bordered">
                            Featured
                        </div>
                        <div class="card-block">
                            <h4 class="card-title">Lorem ipsum dolor sit amet</h4>
                            <p class="card-text">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Lorem ipsum dolor sit amet</h4>
                            <p class="card-text">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                        <div class="card-footer card-footer-transparent card-footer-bordered text-muted">
                            Featured
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h4 class="example-title">Text alignment</h4>
                    <p>You can quickly change the text alignment of any card—in its entirety
                        or specific parts—with our text align classes.</p>
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Lorem ipsum dolor sit amet</h4>
                            <p class="card-text">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>

                    <div class="card text-center">
                        <div class="card-block">
                            <h4 class="card-title">Lorem ipsum dolor sit amet</h4>
                            <p class="card-text">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>

                    <div class="card text-right">
                        <div class="card-block">
                            <h4 class="card-title">Lorem ipsum dolor sit amet</h4>
                            <p class="card-text">Excepteur sint occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.</p>
                            <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Example Card Content -->

        <!-- Example Card Content -->
        <div class="example-wrap">
            <h3 class="example-title">Variants</h3>
            <div class="row">
                <div class="col-md-6">
                    <h4 class="example-title">Background variants</h4>
                    <p>Cards include their own variant classes for quickly changing the
                        background-color and border-color of a card. Darker colors require
                        the use of <code>.card-inverse</code>.</p>
                    <div class="card card-inverse bg-primary">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card card-inverse bg-success">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card card-inverse bg-info">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card card-inverse bg-warning">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card card-inverse bg-danger">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <h4 class="example-title">Outline variants</h4>
                    <p>Cards include their own variant classes for quickly changing the
                        background-color and border-color of a card. Darker colors require
                        the use of <code>.border</code>and<code>.border-primary</code>.</p>
                    <div class="card border  border-primary">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card border border-success">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card border border-info">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card border border-warning">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                    <div class="card border border-danger">
                        <div class="card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">Lorem ipsum Dolor dolor enim Ut consequat tempor quis minim enim
                                sit ad in qui Ut in ut elit minim quis eiusmod reprehenderit
                                aute cillum consequat enim Ut veniam labore dolor anim quis
                                ullamco in cupidatat dolore ut amet elit sint magna exercitation
                                aliquip.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Example Card Content -->

        <div class="row">
            <div class="col-12">
                <!-- Example Card Decks -->
                <div class="example-wrap">
                    <h4 class="example-title">Decks</h4>
                    <p>By default, card decks require two wrapping elements: <code>.card-deck-wrapper</code>                and a <code>.card-deck</code>. We use table styles for the sizing
                        and the gutters on <code>.card-deck</code>. The <code>.card-deck-wrapper</code>                is used to negative margin out the border-spacing on the <code>.card-deck</code>.</p>
                    <p>ProTip! If you enable flexbox mode, you can remove the <code>.card-deck-wrapper</code>.</p>
                    <div class="card-deck">
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Example Card Decks -->
            </div>

            <div class="col-12">
                <!-- Example Card Groups -->
                <div class="example-wrap">
                    <h4 class="example-title">Groups</h4>
                    <p>By default, card groups use <code>display: table;</code> and <code>table-layout: fixed;</code>                to achieve their uniform sizing. However, enabling flexbox mode
                        can switch that to use <code>display: flex;</code> and provide
                        the same effect.</p>
                    <div class="card-group">
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card bg-grey-100">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card bg-grey-100">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">Excepteur sint occaecat cupidatat non laborum.</p>
                                <p class="card-text hidden-md-down">Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Example Card Group -->
            </div>

            <div class="col-12">
                <!-- Example Card Columns -->
                <div class="example-wrap">
                    <h4 class="example-title">Columns</h4>
                    <p>Cards can be organized into Masonry-like columns with just CSS by
                        wrapping them in .card-columns. Heads up! This is not available
                        in IE9 and below as they have no support for the column CSS properties.</p>
                    <div class="card-columns">
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title that wraps to a new line</h4>
                                <p class="card-text">This is a longer card with supporting text below as a natural
                                    lead-in to additional content. This content is a little bit
                                    longer.</p>
                            </div>
                        </div>
                        <div class="card card-block">
                            <blockquote class="blockquote cover-quote card-blockquote">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer
                                    posuere erat a ante.</p>
                                <footer>
                                    <small class="text-muted">
                                        Someone famous in
                                        <cite title="Source Title">Source Title</cite>
                                    </small>
                                </footer>
                            </blockquote>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">This card has supporting text below as a natural lead-in to
                                    additional content.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">This card has supporting text below as a natural lead-in to
                                    additional content.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">This card has supporting text below as a natural lead-in to
                                    additional content.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">This card has supporting text below as a natural lead-in to
                                    additional content.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">This card has supporting text below as a natural lead-in to
                                    additional content.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                        <div class="card card-block card-inverse bg-primary text-center">
                            <blockquote class="blockquote cover-quote card-blockquote">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer
                                    posuere erat.</p>
                                <footer>
                                    <small>
                                        Someone famous in
                                        <cite title="Source Title">Source Title</cite>
                                    </small>
                                </footer>
                            </blockquote>
                        </div>
                        <div class="card card-block text-center">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">This card has supporting text below as a natural lead-in to additional
                                content.</p>
                            <p class="card-text">
                                <small class="text-muted">Last updated 3 mins ago</small>
                            </p>
                        </div>
                        <div class="card">
                            <img class="card-img w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image">
                        </div>
                        <div class="card card-block text-right">
                            <blockquote class="blockquote cover-quote card-blockquote">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer
                                    posuere erat a ante.</p>
                                <footer>
                                    <small class="text-muted">
                                        Someone famous in
                                        <cite title="Source Title">Source Title</cite>
                                    </small>
                                </footer>
                            </blockquote>
                        </div>
                        <div class="card card-block">
                            <h4 class="card-title">Card title</h4>
                            <p class="card-text">This is a wider card with supporting text below as a natural
                                lead-in to additional content. This card has even longer content
                                than the first to show that equal height action.</p>
                            <p class="card-text">
                                <small class="text-muted">Last updated 3 mins ago</small>
                            </p>
                        </div>
                        <div class="card">
                            <img class="card-img-top w-full" src="/Student_Management_System/assets/global/photos/placeholder.png" alt="Card image cap">
                            <div class="card-block">
                                <h4 class="card-title">Card title</h4>
                                <p class="card-text">This card has supporting text below as a natural lead-in to
                                    additional content.</p>
                                <p class="card-text">
                                    <small class="text-muted">Last updated 3 mins ago</small>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Example Card Columns -->
            </div>
        </div>
    </div>
    <!-- End Page Content -->
</div>
