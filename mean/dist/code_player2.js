// photos from flickr with creative commons license

var cy = cytoscape({
  container: document.getElementById('cy'),

  boxSelectionEnabled: false,
  autounselectify: true,

  style: cytoscape.stylesheet()
    .selector('node')
      .css({
        'height': 80,
        'width': 80,
        'background-fit': 'cover',
        'border-color': '#000',
        'border-width': 3,
        'border-opacity': 0.5
      })
    .selector('.other-working')
      .css({
        'border-width': 20,
        'border-color': 'blue'
      })
    .selector('.self-working')
      .css({
        'border-width': 20,
        'border-color': 'red'
      })
    .selector('.completed')
      .css({
        'border-width': 20,
        'border-color': 'green'
      })
    .selector('.none-working')
      .css({
        'border-width': 20,
        'border-color': 'black'
      })
    .selector('.tool')
      .css({
        'border-width': 0,
        'border-color': 'black'
      })
    .selector('node[label]')
      .css({
        "label": "data(label)"
      })
    .selector('edge[label]')
      .css({
        "label": "data(label)",
        "width": 3
      })
    .selector('.outline')
      .css({
        "color": "#fff",
        "text-outline-color": "#888",
        "text-outline-width": 3
      })
    .selector('edge')
      .css({
        'curve-style': 'bezier',
        'width': 6,
        'target-arrow-shape': 'triangle',
        'line-color': '#ffaaaa',
        'target-arrow-color': '#ffaaaa'
      })
    .selector('#RED_WOOL').css({'background-image':'img/materials/RED_WOOL'}).selector('#MAGENTA_WOOL').css({'background-image':'img/materials/MAGENTA_WOOL'}).selector('#SOUL_SAND').css({'background-image':'img/materials/SOUL_SAND'}).selector('#ORANGE_WOOL').css({'background-image':'img/materials/ORANGE_WOOL'}).selector('#BROWN_WOOL').css({'background-image':'img/materials/BROWN_WOOL'}).selector('#JUNGLE_PLANKS').css({'background-image':'img/materials/JUNGLE_PLANKS'}).selector('#ACACIA_PLANKS').css({'background-image':'img/materials/ACACIA_PLANKS'}).selector('#DARK_OAK_PLANKS').css({'background-image':'img/materials/DARK_OAK_PLANKS'})
    /*
    .selector('#bird')
      .css({
        'background-image': 'https://live.staticflickr.com/7272/7633179468_3e19e45a0c_b.jpg'
      })
    .selector('#cat')
      .css({
        'background-image': 'https://live.staticflickr.com/1261/1413379559_412a540d29_b.jpg'
      })
    .selector('#ladybug')
      .css({
        'background-image': 'https://live.staticflickr.com/3063/2751740612_af11fb090b_b.jpg'
      })
    .selector('#aphid')
        .css({
          'background-image': 'https://live.staticflickr.com/8316/8003798443_32d01257c8_b.jpg'
        })
    .selector('#rose')
        .css({
          'background-image': 'https://live.staticflickr.com/5109/5817854163_eaccd688f5_b.jpg'
        })
    .selector('#grasshopper')
        .css({
          'background-image': 'https://live.staticflickr.com/6098/6224655456_f4c3c98589_b.jpg'
        })
    .selector('#plant')
        .css({
          'background-image': 'https://live.staticflickr.com/3866/14420309584_78bf471658_b.jpg'
        })
    .selector('#wheat')
        .css({
          'background-image': 'https://live.staticflickr.com/2660/3715569167_7e978e8319_b.jpg'
        })*/,

  elements: {
    nodes: [
      { data: { id: 'RED_WOOL', label: 'Breakable with IRON_HOE' }, classes: ['none-working', 'outline'] },{ data: { id: 'MAGENTA_WOOL', label: 'Breakable with DIAMOND_AXE' }, classes: ['none-working', 'outline'] },{ data: { id: 'SOUL_SAND', label: 'Breakable with IRON_HOE' }, classes: ['none-working', 'outline'] },{ data: { id: 'ORANGE_WOOL', label: 'Breakable with DIAMOND_AXE' }, classes: ['none-working', 'outline'] },{ data: { id: 'BROWN_WOOL', label: 'Breakable with DIAMOND_AXE' }, classes: ['none-working', 'outline'] },{ data: { id: 'JUNGLE_PLANKS', label: 'Breakable with ANY TOOL' }, classes: ['tool', 'outline'] },{ data: { id: 'ACACIA_PLANKS', label: 'Breakable with ANY TOOL' }, classes: ['tool', 'outline'] },{ data: { id: 'DARK_OAK_PLANKS', label: 'Breakable with ANY TOOL' }, classes: ['tool', 'outline'] }
      /*
      { data: { id: 'cat' }, classes: ['none-working'] },
      { data: { id: 'bird' }, classes: ['none-working'] },
      { data: { id: 'ladybug' }, classes: ['none-working'] },
      { data: { id: 'aphid' }, classes: ['none-working'] },
      { data: { id: 'rose' }, classes: ['none-working'] },
      { data: { id: 'grasshopper' }, classes: ['tool'] },
      { data: { id: 'plant' }, classes: ['none-working'] },
      { data: { id: 'wheat' }, classes: ['none-working'] }*/
    ],
    edges: [
      { data: { source: 'JUNGLE_PLANKS', target: 'MAGENTA_WOOL' } },{ data: { source: 'ACACIA_PLANKS', target: 'SOUL_SAND' } },{ data: { source: 'DARK_OAK_PLANKS', target: 'BROWN_WOOL' } },{ data: { source: 'ORANGE_WOOL', target: 'RED_WOOL' } },{ data: { source: 'SOUL_SAND', target: 'RED_WOOL' } },{ data: { source: 'MAGENTA_WOOL', target: 'ORANGE_WOOL' } },{ data: { source: 'BROWN_WOOL', target: 'ORANGE_WOOL' } }
      /*
      { data: { source: 'cat', target: 'bird' } },
      { data: { source: 'bird', target: 'ladybug' } },
      { data: { source: 'bird', target: 'grasshopper' } },
      { data: { source: 'grasshopper', target: 'plant' } },
      { data: { source: 'grasshopper', target: 'wheat' } },
      { data: { source: 'ladybug', target: 'aphid' } },
      { data: { source: 'aphid', target: 'rose' } }*/
    ]
  },

  layout: {
    name: 'breadthfirst',
    directed: true,
    padding: 10
  }
}); // cy init

cy.on('tap', 'node', function(){
  var node = this;
  if (node.className().includes('none-working')) {
    node.classes(['self-working', 'outline']);
  } else if (node.className().includes('self-working')) {
    node.classes(['other-working', 'outline']);
  } else if (node.className().includes('other-working')) {
    node.classes(['completed', 'outline']);
  } else if (node.className().includes('completed')) {
    node.classes(['none-working', 'outline']);
  }
  // TODO: Add sending server state update message

}); // on tap