<h2>
  Hello World
</h2>

<div id="SystemTableContainer" style="width: 100%;"></div>
<script type="text/javascript">

    $(document).ready(function () {

        //Prepare jTable
        $('#SystemTableContainer').jtable({
            title: 'Table of systems',
            actions: {
                listAction: 'api/system/list',
                createAction: 'api/system/add',
                updateAction: 'api/system/update'
            },
            fields: {
                id: {
                    key: true,
                    create: false,
                    edit: false
                },
                name: {
                    title: 'System name',
                    width: '20%'
                },
                description: {
                    title: 'Description',
                    width: '40%'
                },
                techs: {
                    title: 'Technologies',
                    width: '40%'
                }
            }
        });
        $('#SystemTableContainer').jtable('load');

    });

</script>