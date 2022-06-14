#execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:0b}]} run item replace block ~ ~ ~ container.0 with minecraft:jigsaw{CustomModelData:124401,display:{Name:'{"text":""}'},gui_item:1b}
execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:1b}]} run item replace block ~ ~ ~ container.1 with minecraft:jigsaw{CustomModelData:124410,display:{Name:'{"text":""}'},gui_item:1b}
#execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:2b}]} run item replace block ~ ~ ~ container.2 with minecraft:jigsaw{CustomModelData:124401,display:{Name:'{"text":""}'},gui_item:1b}
execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:3b}]} run item replace block ~ ~ ~ container.3 with minecraft:jigsaw{CustomModelData:124401,display:{Name:'{"text":""}'},gui_item:1b}

execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:4b}]} run function expansion:blocks/arc_furnace/fix_gui_tank

execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:5b}]} run item replace block ~ ~ ~ container.5 with minecraft:jigsaw{CustomModelData:124401,display:{Name:'{"text":""}'},gui_item:1b}
execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:6b}]} run item replace block ~ ~ ~ container.6 with minecraft:jigsaw{CustomModelData:124401,display:{Name:'{"text":""}'},gui_item:1b}
execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:7b}]} run item replace block ~ ~ ~ container.7 with minecraft:jigsaw{CustomModelData:124451,display:{Name:'{"text":""}'},gui_item:1b}

execute if score @s exp_timer_2 matches 0.. unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:7b,id:"minecraft:jigsaw",tag:{CustomModelData:124450}}]} run item replace block ~ ~ ~ container.7 with minecraft:jigsaw{CustomModelData:124450,display:{Name:'{"text":""}'},gui_item:1b}
execute unless score @s exp_timer_2 matches 0.. unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:7b,id:"minecraft:jigsaw",tag:{CustomModelData:124451}}]} run item replace block ~ ~ ~ container.7 with minecraft:jigsaw{CustomModelData:124451,display:{Name:'{"text":""}'},gui_item:1b}



#execute unless block ~ ~ ~ minecraft:dropper{Items:[{Slot:8b}]} run item replace block ~ ~ ~ container.8 with minecraft:jigsaw{CustomModelData:124401,display:{Name:'{"text":""}'},gui_item:1b}
