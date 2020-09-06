
describe 'simditor-checklist', ->
  editor = null
  simditor = null


  generateSimditor = ->
    content = '''
      <p>Simditor 是团队协作工具 <a href="http://tower.im">Tower</a> 使用的富文本编辑器。</p>
      <p>相比传统的编辑器它的特点是：</p>
      <ul>
        <li>功能精简，加载快速</li>
        <li>输出格式化的标准 HTML</li>
        <li>每一个功能都有非常优秀的使用体验</li>
      </ul>
      <p>兼容的浏览器：IE10+、Chrome、Firefox、Safari。</p>
    '''

    $textarea = $('<textarea id="editor"></textarea>')
      .val(content)
      .appendTo 'body'
    simditor = new Simditor
      textarea: $textarea
      toolbar: ['checklist', 'title', 'bold', 'italic', 'underline']

  destroySimditor = ->
    $textarea = $('#editor')
    editor = $textarea.data 'simditor'
    editor?.destroy()
    $textarea.remove()

  beforeEach ->
    editor = generateSimditor()

  afterEach ->
    destroySimditor()
    editor = null
    simditor = null

  it 'should render button in simditor', ->
    $simditor = $('.simditor')
    expect($simditor).toExist()
    expect($simditor.find('.simditor-toolbar ul li a.toolbar-item-checklist')).toExist()
