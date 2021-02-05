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
    //$selectors
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
      //$nodes
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
      //$edges
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