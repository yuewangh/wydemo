<#include '/pages/localization/assign.ftl'>
<#macro singleFile id name='fileInfo' extensions=''>
<script type="text/javascript" src="${base}/scripts/plupload/plupload.full.min.js"></script>
<script type="text/javascript">

    var uploader_${id} = new plupload.Uploader({
        runtimes : 'html5,flash,silverlight,browserplus',
        browse_button : '${id}',
        max_file_size : '10mb',
        url : '${base}/upload/file/singleFile.json',
        flash_swf_url : '${base}/scripts/plupload/Moxie.swf',
        silverlight_xap_url : '${base}/scripts/plupload/plupload.silverlight.xap',
        <#if extensions!=''>
        filters : [
            {title : "file", extensions : "${extensions}"}
        ],
        </#if>
        init: {

            FilesAdded: function(up, files) {
                uploader_${id}.start();
            },

            FileUploaded: function(up, file,res) {
                var ${name} = eval("(" + res.response + ")");
                <#nested >
            },

            Error: function(up, err) {
            }
        }
    });

    uploader_${id}.init();
</script>

</#macro>