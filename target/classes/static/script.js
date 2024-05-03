    $(function uploadFile(){
            $('#form').submit(function(){
              var dados = new FormData(this);
              var link = document.getElementById("download");
                var modal = document.getElementById('modal-body');
                var progress = $('#upload');
               $.ajax({
                    url:"/api/arquivo",
                    type:"post",
                    data:dados, 
                    processData:false,
                    contentType:false,
                    error: function(request, status, error){
                        alert(request.responseText);
                        
                    },
                    function(){
                      var xhr = $.ajaxSettings.xhr();
                    
                      xhr.upload.addEventListener("progress", function(e){
                          if(e.lengthComputable){
                              var completed = (e.loaded/e.total)*100;
                              console.log("Upload Progress:", completed)
                              progress.attr("value",completed)
                          }
                      }, false)
                      
                    },
                    success:function(data){
                      console.log(data);
                      link.style.display="block";
                      modal.style.display="block";
                  }

                });
                
                
                return false;
            })
          })
        
        function sortedText(){
            var text = $('#ordenado').val();
            var link = document.getElementById("download-txt");
            $.ajax({
                type:"POST",
                url:"/api/ordem",
                data:text,
                contentType:"application/text; charset=utf-8",
                success:function(data){
                    console.log(data);
                }
  
            });
        }
        function writeAuto(elemento){
          const textArray =elemento.innerHTML.split('');
          elemento.innerHTML= ' ';
          textArray.forEach(function(letra,i){
            setTimeout(function(){
              elemento.innerHTML += letra;
            },75 * i)
          });
        }
        const titulo = document.querySelector('.centralizado');
        writeAuto(titulo);
        
      $(function uploadText(){
        $('#form-index').submit(function(){
          var text = new FormData(this);
          var link2 = document.getElementById("download-file");
          var caixa = document.getElementById("modal-div");
          $.ajax({
            url:"/api/organizar",
            method:"post",
            data: text,
            processData:false,
            contentType:false,
            success:function(data){
              console.log(data);
              caixa.style.display="block"
              link2.style.display="block";
            },
            error: function(request, status, error){
                alert(request.responseText);
                
            }

            
        });
        
        return false;
    })
    
});

